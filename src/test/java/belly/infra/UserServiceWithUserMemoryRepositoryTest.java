package belly.infra;

import belly.service.UserService;

public class UserServiceWithUserMemoryRepositoryTest {

    private UserService service = new UserService(new UserMemoryRepository());
}
