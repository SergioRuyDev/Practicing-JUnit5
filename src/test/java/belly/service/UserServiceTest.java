package belly.service;

import belly.domain.User;
import belly.exceptions.ValidationException;
import belly.service.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static belly.builders.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    //    @AfterEach
//    void tearDown() {
//        verifyNoInteractions(repository);
//    }

    @Test
    public void shouldReturnEmptyWhenUserNotExist() {

//        Mockito.when(repository.getUserByEmail("mail@email.com")).thenReturn(Optional.empty());

        Optional<User> user = service.getUserByEmail("mail@email.com");
        assertTrue(user.isEmpty());
    }

    @Test
    public void shouldReturnUserByEmail() {

        when(repository.getUserByEmail("mail@email.com"))
                .thenReturn(Optional.of(oneUser().createEntity()));

        Optional<User> user = service.getUserByEmail("mail@email.com");

        assertTrue(user.isPresent());

//        verify(repository, atLeastOnce()).getUserByEmail("mail@email.com");
//        verify(repository,never()).getUserByEmail("other@email.com");
//        verifyNoInteractions(repository);
    }

    @Test
    public void shouldSaveUser() {

        User userToSave = oneUser().withId(null).createEntity();

//        when(repository.getUserByEmail(userToSave.getEmail()))
//                .thenReturn(Optional.empty());

        when(repository.save(userToSave))
                .thenReturn(oneUser().createEntity());

        User savedUser = service.save(userToSave);
        assertNotNull(savedUser.getId());

        verify(repository).getUserByEmail(userToSave.getEmail());
        verify(repository).save(userToSave);
    }

    @Test
    public void shouldRejectExistedUser() {

        User userToSave = oneUser().withId(null).createEntity();

        when(repository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(Optional.of(oneUser().createEntity()));

        ValidationException exception = assertThrows(ValidationException.class, () ->
                service.save(userToSave));
//        assertEquals(exception.getMessage(), String.format("User %s is already registered!", userToSave.getEmail()));assertEquals(exception.getMessage(), String.format("User %s is already registered!", userToSave.getEmail()));
        assertTrue(exception.getMessage().endsWith("already registered!"));

        verify(repository, never()).save(userToSave);
    }
}
