package belly.domain;

import belly.domain.exceptions.ValidationException;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

    public User(Long id, String name, String email, String password) {

        if (name == null) throw new ValidationException("Name is mandatory");
        if (email == null) throw new ValidationException("Email is mandatory");
        if (password == null) throw new ValidationException("password is mandatory");

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
