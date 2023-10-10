package belly.domain.builders;

import belly.domain.User;

public class UserBuilder {

    private Long id;
    private String name;
    private String email;
    private String password;

    private UserBuilder() {
    }

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

    public UserBuilder withId(Long param) {
        id = param;
        return this;
    }

    public UserBuilder withName(String param) {
        name = param;
        return this;
    }

    public UserBuilder withEmail(String param) {
        email = param;
        return this;
    }

    public UserBuilder withPassword(String param) {
        password = param;
        return this;
    }

    public User createEntity() {
        return new User(id, name, email, password);
    }
}
