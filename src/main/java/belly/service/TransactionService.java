package belly.service;

import belly.domain.Transaction;
import belly.exceptions.ValidationException;
import belly.service.repositories.TransactionDao;

public class TransactionService {

    TransactionDao transactionDao;

    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction save(Transaction transaction) {
        if (transaction.getDescription() == null) throw new ValidationException("Description don't exists!");
        if (transaction.getValue() == null) throw new ValidationException("Value don't exists!");
        if (transaction.getDate() == null) throw new ValidationException("Date don't exists!");
        if (transaction.getAccount() == null) throw new ValidationException("Account don't exists!");
        if (transaction.getStatus() == null) transaction.setStatus(false);

        return transactionDao.save(transaction);
    }
}

