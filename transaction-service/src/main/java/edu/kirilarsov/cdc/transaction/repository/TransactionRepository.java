package edu.kirilarsov.cdc.transaction.repository;

import edu.kirilarsov.cdc.transaction.model.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 * Transaction repository.
 *
 */
public interface TransactionRepository {

    Optional<List<Transaction>> findByAccountUuid(String accountUuid);

    Optional<List<Transaction>> findByCardUuid(String cardUuid);

    Transaction save(Transaction transaction);

    @Query(value = "SELECT 1", nativeQuery = true)
    void health();
}
