package belly.service;

import belly.domain.Transaction;
import belly.exceptions.ValidationException;
import belly.service.repositories.TransactionDao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionService {

    TransactionDao transactionDao;

    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction save(Transaction transaction) {
        if (LocalDateTime.now().getHour() > 6)
            throw new RuntimeException("Try again tomorrow");

        if (transaction.getDescription() == null) throw new ValidationException("Description don't exists!");
        if (transaction.getValue() == null) throw new ValidationException("Value don't exists!");
        if (transaction.getDate() == null) throw new ValidationException("Date don't exists!");
        if (transaction.getAccount() == null) throw new ValidationException("Account don't exists!");
        if (transaction.getStatus() == null) transaction.setStatus(false);

        return transactionDao.save(transaction);
    }
}

