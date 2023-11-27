package belly.service;

import belly.domain.Account;
import belly.exceptions.ValidationException;
import belly.service.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static belly.builders.AccountBuilder.oneAccount;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService service;

    @Mock
    private AccountRepository repository;

    @Test
    void shouldSaveFirstAccount() {

        Account accountToSave = oneAccount().withId(null).createEntity();

        when(repository.save(accountToSave)).thenReturn(oneAccount().createEntity());

        Account savedAccount = service.save(accountToSave);

        assertNotNull(savedAccount.id());
    }

    @Test
    void shouldSaveSecondAccount() {

        Account accountToSave = oneAccount().withId(null).createEntity();

        when(repository.getAccountByUser(accountToSave.id())).thenReturn(singletonList(oneAccount().withName("Other Account")
                .createEntity()));

        when(repository.save(accountToSave)).thenReturn(oneAccount().createEntity());

        Account savedAccount = service.save(accountToSave);

        assertNotNull(savedAccount.id());
    }

    @Test
    void shouldRejectAccountWhenNameExists() {

        Account accountToSave = oneAccount().withId(null).createEntity();

        when(repository.getAccountByUser(accountToSave.id())).thenReturn(singletonList(oneAccount().createEntity()));

        ValidationException exception = assertThrows(ValidationException.class, () ->
                service.save(accountToSave));

        assertTrue(exception.getMessage().contains("Already exists"));

        verify(repository, never()).save(accountToSave);
    }
}
