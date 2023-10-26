package belly.domain;

import org.junit.jupiter.api.Test;

import static belly.builders.AccountBuilder.oneAccount;
import static belly.builders.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
