package belly.domain;

import belly.exceptions.ValidationException;

import java.util.Objects;

public class Account {

    private Long id;
    private String name;
    private User user;

    public Account(Long id, String name, User user) {

        if (name == null) throw new ValidationException("Name is mandatory");
        if (user == null) throw new ValidationException("User is mandatory");

        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Long id() {
        return id;
    }
    public String name() {
        return name;
    }

    public User user() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(name, account.name) && Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, user);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
