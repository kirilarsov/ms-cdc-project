package edu.kirilarsov.cdc.accountservice.transaction;

import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import org.springframework.stereotype.Service;

/**
 * TransactionService class for providing behaviour on Transaction related data.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponseDto getTransactionsByAccount(String accountUuid) {
        return transactionRepository.getTransactionsByAccount(accountUuid);
    }

}
