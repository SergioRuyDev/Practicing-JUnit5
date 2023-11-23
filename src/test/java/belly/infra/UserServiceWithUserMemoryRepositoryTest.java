package belly.infra;

import belly.domain.User;
import belly.exceptions.ValidationException;
import belly.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static belly.builders.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceWithUserMemoryRepositoryTest {

    private static UserService service = new UserService(new UserMemoryRepository());

    @Test
    @Order(1)
    public void shouldSaveValidUser() {

       User user = service.save(oneUser().withId(null).createEntity());

        assertNotNull(user.getId());
    }

    @Test
    @Order(2)
    public void shouldRejectExistedUser() {

        ValidationException exception = assertThrows(ValidationException.class, () ->
                service.save(oneUser().withId(null).createEntity())
        );
        assertEquals("User user@email.com is already registered!", exception.getMessage());
    }
}
