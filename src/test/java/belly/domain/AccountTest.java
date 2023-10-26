package belly.domain;

import belly.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static belly.builders.AccountBuilder.oneAccount;
import static belly.builders.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {

    @Test
    public void shouldCreateValidAccount() {
        // create account

        Account account = oneAccount().createEntity();

        //Assertions
        assertAll("Account",
                () -> assertEquals(1L, account.id()),
                () -> assertEquals("Valid Account", account.name()),
                () -> assertEquals(oneUser().createEntity(), account.user()));
    }

//    @ParameterizedTest
//    @MethodSource(value = "dataProvider")
//    public void shouldRejectInvalidAccount(Long id, String name, User user, String message) {
//
//        String errorMessage = assertThrows(ValidationException.class,
//                () -> oneAccount().withId(id).withName(name).withUser(user)).getMessage();
//
//        assertEquals(message, errorMessage);
//    }
//
//    private static Stream<Arguments> dataProvider() {
//        return Stream.of(
//                Arguments.of(1L, null, oneUser().createEntity(), "Name is mandatory"),
//                Arguments.of(1L, "Valid Account", null, "User is mandatory")
//        );
//    }
}
