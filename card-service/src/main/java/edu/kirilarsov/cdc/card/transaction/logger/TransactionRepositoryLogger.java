package edu.kirilarsov.cdc.card.transaction.logger;

import edu.kirilarsov.cdc.card.transaction.TransactionRepository;
import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Logger impl of transaction repository.
 *
 */
@Component
@Profile("transaction-logger-client")
public class TransactionRepositoryLogger implements TransactionRepository {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryLogger.class);

    @Override
    public TransactionResponseDto getTransactionsByCard(String cardUuid) {
        logger.info("Method invoked: getTransactionsByAccount() from TransactionRepositoryLogger");
        return new TransactionResponseDto(
            List.of(new TransactionResponseDto.TransactionDto(
                "account-uuid-1",
                "card-uuid",
                "IKEA",
                "251.52",
                "EUR",
                TransactionResponseDto.TransactionDto.Type.SETTLEMENT
            )));
    }
}
