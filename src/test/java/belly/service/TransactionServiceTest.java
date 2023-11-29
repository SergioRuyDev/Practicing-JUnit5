package belly.service;

import belly.domain.Transaction;
import belly.service.repositories.TransactionDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static belly.builders.TransactionBuilder.oneTransaction;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionDao transactionDao;

    @Test
    void shouldSaveValidTransaction() {

        Transaction transactionToSave = oneTransaction().withId(null).createEntity();

        when(transactionDao.save(transactionToSave)).thenReturn(oneTransaction().createEntity());

        Transaction transactionSaved = transactionService.save(transactionToSave);
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
                    });

                }
        );
    }
}
