package belly.service;

import belly.domain.User;
import belly.exceptions.ValidationException;
import belly.service.repositories.UserRepository;

import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        userRepository.getUserByEmail(user.getEmail()).ifPresent(user1 -> {
            throw new ValidationException(String.format("User %s is already registered!", user.getEmail()));
        });
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
