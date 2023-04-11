package edu.kirilarsov.cdc.accountservice.transaction.web;

import edu.kirilarsov.cdc.accountservice.transaction.TransactionRepository;
import edu.kirilarsov.cdc.transaction.client.TransactionClient;
import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Transaction Repository Web Impl.
 */
@Component
@Profile("transaction-web-client")
public class TransactionRepositoryWeb implements TransactionRepository {
    private final TransactionClient transactionClient;

    public TransactionRepositoryWeb(TransactionClient transactionClient) {
        this.transactionClient = transactionClient;
    }

    @Override
    public TransactionResponseDto getTransactionsByAccount(String accountUuid) {
        return transactionClient.getTransactionsByAccount(accountUuid);
    }

}