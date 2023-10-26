package belly.domain;

import belly.User;
import belly.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static belly.builders.UserBuilder.oneUser;
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

    @ParameterizedTest(name = "[{index}] - {4}")
    @CsvSource(value = {
            "1, NULL, user@mail.com, 123456, Name is mandatory",
            "1, User Name, NULL, 123456, Email is mandatory",
            "1, User Name, user@mail.com, NULL, password is mandatory"
    }, nullValues = "NULL")
    public void shouldRejectUserWithoutParameters(Long id, String name, String email, String password, String message) {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                oneUser().withId(id).withName(name).withEmail(email).withPassword(password).createEntity());
        assertEquals(message, exception.getMessage());
    }
}
