package belly.service;

import belly.domain.Account;
import belly.exceptions.ValidationException;
import belly.service.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {

        List<Account> accounts = accountRepository.getAccountByUser(account.id());
        accounts.forEach(existsAccount -> {
            if (account.name().equalsIgnoreCase(existsAccount.name()))
                throw new ValidationException("Already exists user with this name.");
        });

        return accountRepository.save(account);
    }

    public Optional<Account> getByName(String name) {
        return accountRepository.getByName(name);
    }
}
