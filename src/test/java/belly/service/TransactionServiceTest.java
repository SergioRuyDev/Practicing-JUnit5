package belly.service;

import belly.builders.AccountBuilder;
import belly.domain.Account;
import belly.domain.Transaction;
import belly.exceptions.ValidationException;
import belly.service.repositories.TransactionDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static belly.builders.TransactionBuilder.oneTransaction;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionDao dao;

    @Test
    public  void shouldSaveValidTransaction() {
        Transaction transactionToSave = oneTransaction().withId(null).createEntity();
        when(dao.save(transactionToSave)).thenReturn(oneTransaction().createEntity());

        Transaction transactionSaved = service.save(transactionToSave);

        assertEquals(oneTransaction().createEntity(), transactionSaved);

        assertAll("Transaction",
                () -> assertEquals(1L, transactionSaved.getId()),
                () -> assertEquals("Valid Transaction", transactionSaved.getDescription()),
                () -> {
            assertAll("Account",
                    () -> assertEquals("Valid Account", transactionSaved.getAccount().name()),
                    () -> {
                assertAll("User",
                        () -> assertEquals("Valid User", transactionSaved.getAccount().user().getName()),
                        () -> assertEquals("123456", transactionSaved.getAccount().user().getPassword())
                    );
                  }
                );
            }
        );
    }

    @ParameterizedTest(name = "{6}")
    @MethodSource(value = "requirmentsCenarios")
    public void shouldValidateRequirementsBeforeSave(Long id, String description, Double value,
                                                     LocalDate date, Account account, Boolean status, String message) {

        String errorMessage = assertThrows(ValidationException.class, () -> {
            Transaction transactionToSave = oneTransaction().withId(id).withDescription(description).withValue(value)
                    .withDate(date).withStatus(status).withAccount(account).createEntity();
            service.save(transactionToSave);
        }).getMessage();

        assertEquals(message, errorMessage);

    }

    static Stream<Arguments> requirmentsCenarios() {
        return Stream.of(
                Arguments.of(1L, null, 10D, LocalDate.now(), AccountBuilder.oneAccount().createEntity(), true,
                        "Description don't exists!"),
                Arguments.of(1L, "Description", null, LocalDate.now(), AccountBuilder.oneAccount().createEntity(), true,
                        "Value don't exists!"),
                Arguments.of(1L, "Description", 10D, null, AccountBuilder.oneAccount().createEntity(), true,
                        "Date don't exists!"),
                Arguments.of(1L, "Description", 10D, LocalDate.now(), null, true,
                        "Account don't exists!")
        );
    }

}
