package edu.kirilarsov.cdc.card.controller.dto;

import java.util.List;

/**
 * CardsWithTransactionDto.
 */
public record CardsWithTransactionDto(List<CardDto> cards) {

    /**
     * CardDto.
     */
    public static record CardDto(String accountUuid, String cardHolderName, String pan, String validTo, String cvc,
                                 CardDto.Status status, List<TransactionDto> transactions) {

        /**
         * Status.
         */
        public static enum Status {
            ACTIVE,
            BLOCKED,
            INACTIVE,
            IN_PROGRESS,
            CANCELLED,
            LOST,
            STOLEN,
            DAMAGED;
        }

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
}