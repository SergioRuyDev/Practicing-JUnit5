package belly.domain;

import belly.exceptions.ValidationException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getName(), user.getName())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getPassword());
    }
}
