package edu.kirilarsov.cdc.accountservice.transaction;


import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;

/**
 * TransactionRepository interface for defining access behaviour for the transaction-service.
 */
public interface TransactionRepository {

    TransactionResponseDto getTransactionsByAccount(String accountUuid);

}
