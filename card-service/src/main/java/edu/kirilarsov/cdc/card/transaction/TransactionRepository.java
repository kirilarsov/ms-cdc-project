package edu.kirilarsov.cdc.card.transaction;


import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;

/**
 * TransactionRepository interface for defining access behaviour for the transaction-service.
 */
public interface TransactionRepository {

    TransactionResponseDto getTransactionsByCard(String cardUuid);

}
