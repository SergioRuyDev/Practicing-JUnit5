package belly.domain;

import belly.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Should create a valid user.")
    public void shouldCreateValidUser() {
        User user = new User(1L, "Valid User", "user@email.com", "123456");

        assertAll("User",
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("Valid User", user.getName()),
                () -> assertEquals("user@email.com", user.getEmail()),
                () -> assertEquals("123456", user.getPassword())
        );
    }

    @Test
    @DisplayName("Should reject user without name.")
    public void shouldRejectUserWithoutName() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                new User(1L, null, "user@email.com", "123456"));
        assertEquals("Name is mandatory", exception.getMessage());
    }
}
