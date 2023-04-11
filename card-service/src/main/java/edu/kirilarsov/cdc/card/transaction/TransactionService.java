package edu.kirilarsov.cdc.card.transaction;

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

    public TransactionResponseDto getTransactionsByCard(String cardUuid) {
        return transactionRepository.getTransactionsByCard(cardUuid);
    }

}
