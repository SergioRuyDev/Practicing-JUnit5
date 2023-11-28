package belly.service.repositories;

import belly.domain.Transaction;

public interface TransactionDao {

    Transaction save(Transaction transaction);
}
