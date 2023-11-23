package belly.service;

import belly.domain.Account;
import belly.exceptions.ValidationException;
import belly.service.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static belly.builders.AccountBuilder.oneAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService service;

    @Mock
    private AccountRepository repository;

    @Test
    void shouldSaveAccount() {

        Account accountToSave = oneAccount().withId(null).createEntity();

        when(repository.save(accountToSave)).thenReturn(oneAccount().createEntity());

        Account savedAccount = service.save(accountToSave);

        assertNotNull(savedAccount.id());
    }

    @Test
    void shouldRejectAccountWhenNameExists() {

        Account accountToSave = oneAccount().withId(null).createEntity();

        when(repository.getByName(accountToSave.name())).thenReturn(Optional.of(oneAccount().createEntity()));

        ValidationException exception = assertThrows(ValidationException.class, () ->
                service.save(accountToSave));

        assertTrue(exception.getMessage().endsWith("already exists!"));

        verify(repository, never()).save(accountToSave);
    }
}
