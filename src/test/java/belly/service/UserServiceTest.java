package belly.service;

import belly.builders.UserBuilder;
import belly.domain.User;
import belly.service.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService service;

    @Test
    public void shouldReturnEmptyWhenUserNotExist() {

        UserRepository repository = Mockito.mock(UserRepository.class);

        service = new UserService(repository);

//        Mockito.when(repository.getUserByEmail("mail@email.com")).thenReturn(Optional.empty());

        Optional<User> user = service.getUserByEmail("mail@email.com");
        assertTrue(user.isEmpty());
    }

    @Test
    public void shouldReturnUserByEmail() {

        UserRepository repository = Mockito.mock(UserRepository.class);

        service = new UserService(repository);

        when(repository.getUserByEmail("mail@email.com"))
                .thenReturn(Optional.of(UserBuilder.oneUser().createEntity()));

        Optional<User> user = service.getUserByEmail("mail@email.com");
        assertTrue(user.isPresent());
        verify(repository).getUserByEmail("mail@email.com");
        verify(repository, times(1)).getUserByEmail("mail@email.com");
    }
}
