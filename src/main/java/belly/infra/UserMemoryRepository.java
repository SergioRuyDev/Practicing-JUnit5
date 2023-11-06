package belly.infra;

import belly.domain.User;
import belly.service.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMemoryRepository implements UserRepository {

    private List<User> users;
    private Long currentId;

    public UserMemoryRepository() {
        currentId = 0L;
        users = new ArrayList<>();
        save(new User(null, "User #1", "user@mail.com", "123456"));
    }


    @Override
    public User save(User user) {
        User newUser = new User(nextId(), user.getName(), user.getEmail(), user.getPassword());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void printUsers() {
        System.out.println(users);
    }

    private Long nextId() {
        return ++currentId;
    }

    public static void main(String[] args) {
        UserMemoryRepository repo = new UserMemoryRepository();
        repo.printUsers();
        repo.save(new User(null, "User wherever", "a@a.com", "aaa"));
        repo.printUsers();
    }
}
