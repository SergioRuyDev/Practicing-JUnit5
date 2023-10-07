package belly.domain.builders;

public class UserBuilder {

    private Long id;
    private String name;
    private String email;
    private String password;

    private UserBuilder() {
    }

    public static UserBuilder oneUser() {
        UserBuilder builder = new UserBuilder();
        initializeStandardAtributes(builder);
        return builder;
    }

    private static void initializeStandardAtributes(UserBuilder builder) {
        builder.id = 1L;
        builder.name = "Valid User";
        builder.email = "user@email.com";
        builder.password = "123456";
    }
}
