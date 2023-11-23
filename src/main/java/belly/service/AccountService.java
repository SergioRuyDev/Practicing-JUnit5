package belly.service;

import belly.domain.Account;
import belly.exceptions.ValidationException;
import belly.service.repositories.AccountRepository;

import java.util.Optional;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {
        accountRepository.getByName(account.name()).ifPresent(account1 -> {
            throw new ValidationException(String.format("Account with name %s already exists!", account.name()));
        });
        return accountRepository.save(account);
    }

    public Optional<Account> getByName(String name) {
        return accountRepository.getByName(name);
    }
}
