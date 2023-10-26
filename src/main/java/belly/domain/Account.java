package belly.domain;

import belly.User;

public class Account {

    private Long id;
    private String name;
    private User user;

    public Account(Long id, String name, User user) {
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
}
