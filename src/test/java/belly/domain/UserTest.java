package belly.domain;

import belly.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static belly.domain.builders.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Should create a valid user.")
    public void shouldCreateValidUser() {
        User user = oneUser().createEntity();

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
                oneUser().withName(null).createEntity());
        assertEquals("Name is mandatory", exception.getMessage());
    }

    @Test
    @DisplayName("Should reject user without e-mail.")
    public void shouldRejectUserWithoutEmail() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                oneUser().withEmail(null).createEntity());
        assertEquals("Email is mandatory", exception.getMessage());
    }

    @Test
    @DisplayName("Should reject user without password.")
    public void shouldRejectUserWithoutPassword() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                oneUser().withPassword(null).createEntity());
        assertEquals("password is mandatory", exception.getMessage());
    }
}
