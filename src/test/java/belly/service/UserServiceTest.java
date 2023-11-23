package belly.service;

import belly.domain.User;
import belly.service.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static belly.builders.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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
}
