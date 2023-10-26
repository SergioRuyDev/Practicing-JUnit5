package belly.builders;

import belly.User;

public class UserBuilder {
    private Long id;
    private String name;
    private String email;
    private String password;

    private UserBuilder(){}

    public static UserBuilder oneUser() {
        UserBuilder builder = new UserBuilder();
        initializeStandardAttributes(builder);
        return builder;
    }

    private static void initializeStandardAttributes(UserBuilder builder) {
        builder.id = 1L;
        builder.name = "Valid User";
        builder.email = "user@email.com";
        builder.password = "123456";
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public User createEntity() {
        return new User(id, name, email, password);
    }
}
