package edu.kirilarsov.cdc.transaction.service;

import edu.kirilarsov.cdc.transaction.model.Transaction;
import edu.kirilarsov.cdc.transaction.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * TransactionService for retrieving transactions.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Optional<List<Transaction>> getTransactionsByAccount(String accountUuid) {
        return transactionRepository.findByAccountUuid(accountUuid);
    }

    public Optional<List<Transaction>> getTransactionsByCard(String cardUuid) {
        return transactionRepository.findByCardUuid(cardUuid);
    }
}
