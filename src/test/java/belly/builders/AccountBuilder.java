package belly.builders;

import belly.domain.User;
import belly.domain.Account;

public class AccountBuilder {
    private Long id;
    private String name;
    private User user;

    private AccountBuilder(){}

    public static AccountBuilder oneAccount() {
        AccountBuilder builder = new AccountBuilder();
        initializeStandardAttributes(builder);
        return builder;
    }

    private static void initializeStandardAttributes(AccountBuilder builder) {
        builder.id = 1L;
        builder.name = "Valid Account";
        builder.user = UserBuilder.oneUser().createEntity();
    }

    public AccountBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AccountBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Account createEntity() {
        return new Account(id, name, user);
    }
}
