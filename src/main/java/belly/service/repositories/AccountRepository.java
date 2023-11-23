package belly.service.repositories;

import belly.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> getByName(String name);

    List<Account> getAccountByUser(Long userId);
}
