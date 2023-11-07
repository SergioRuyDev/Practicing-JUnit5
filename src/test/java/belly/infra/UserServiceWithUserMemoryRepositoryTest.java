package belly.infra;

import belly.domain.User;
import belly.exceptions.ValidationException;
import belly.service.UserService;
import org.junit.jupiter.api.Test;

import static belly.builders.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceWithUserMemoryRepositoryTest {

    private static UserService service = new UserService(new UserMemoryRepository());

    @Test
    public void shouldSaveValidUser() {

       User user = service.save(oneUser().withId(null).createEntity());

        assertNotNull(user.getId());
    }

    @Test
    public void shouldRejectExistedUser() {

        ValidationException exception = assertThrows(ValidationException.class, () ->
                service.save(oneUser().withId(null).createEntity())
        );
        assertEquals("asasasa", exception.getMessage());
    }

}
