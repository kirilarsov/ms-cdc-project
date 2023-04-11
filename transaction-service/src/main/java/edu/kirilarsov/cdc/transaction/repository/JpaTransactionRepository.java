package edu.kirilarsov.cdc.transaction.repository;


import edu.kirilarsov.cdc.transaction.model.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Transaction repository.
 *
 */
@Primary
@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepository {

}
