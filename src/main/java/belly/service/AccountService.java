package belly.service;

import belly.domain.Account;
import belly.exceptions.ValidationException;
import belly.service.events.AccountEvent;
import belly.service.repositories.AccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static belly.service.events.AccountEvent.EventType.CREATED;

public class AccountService {

    private AccountRepository accountRepository;

    private AccountEvent event;

    public AccountService(AccountRepository accountRepository, AccountEvent event) {
        this.accountRepository = accountRepository;
        this.event = event;
    }

    public Account save(Account account) {

        List<Account> accounts = accountRepository.getAccountByUser(account.id());
        accounts.forEach(existsAccount -> {
            if (account.name().equalsIgnoreCase(existsAccount.name()))
                throw new ValidationException("Already exists user with this name.");
        });

        Account accountSaved = accountRepository.save(
                new Account(account.id(), account.name() + LocalDateTime.now(), account.user()));
        try {
            event.dispatch(accountSaved, CREATED);
        } catch (Exception exception) {
            accountRepository.delete(accountSaved);
            throw new RuntimeException("Failed to create account, try again.");
        }
        return accountSaved;
    }

    public Optional<Account> getByName(String name) {
        return accountRepository.getByName(name);
    }
}
