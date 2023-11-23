package belly.infra;

import belly.domain.User;
import belly.service.repositories.UserRepository;

import java.util.Optional;

import static belly.builders.UserBuilder.oneUser;

public class UserDummyRepository implements UserRepository {


    @Override
    public User save(User user) {
        return oneUser()
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .createEntity();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if ("user@email.com".equals(email))
            return Optional.of(oneUser().withEmail(email).createEntity());
        return Optional.empty();
    }
}
