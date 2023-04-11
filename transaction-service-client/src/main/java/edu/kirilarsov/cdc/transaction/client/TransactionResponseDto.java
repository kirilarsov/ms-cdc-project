package edu.kirilarsov.cdc.transaction.client;

import java.util.List;

/**
 * TransactionResponseDto record for response dto objects.
 */
public record TransactionResponseDto(List<TransactionDto> transactions) {

    /**
     * TransactionDto record for response dto objects.
     */
    public record TransactionDto(
        String accountUuid,
        String cardUuid,
        String merchant,
        String amount,
        String currency,
        Type type) {

        /**
         * enum for defining transaction statuses.
         */
        public enum Type {
            AUTHORIZATION, SETTLEMENT, LOAD, UNLOAD, CHARGEBACK, REVERSAL, REFUND
        }
    }
}