package edu.kirilarsov.cdc.accountservice.controller.dto;

import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import java.math.BigDecimal;
import java.util.List;

/**
 * AccountResponseDto record for response dto objects.
 */
public record AccountWithTransactionsResponseDto(List<AccountDto> accounts) {

    /**
     * AccountDto record for response dto objects.
     */
    public record AccountDto(
        String userUuid,
        String name,
        BigDecimal balance,
        Currency currency,
        List<TransactionDto> transactions) {

        /**
         * Currency record for response dto objects.
         */
        public enum Currency {
            EUR, GBP
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
            TransactionResponseDto.TransactionDto.Type type) {

            /**
             * enum for defining transaction statuses.
             */
            public enum Type {
                AUTHORIZATION, SETTLEMENT, LOAD, UNLOAD, CHARGEBACK, REVERSAL, REFUND
            }
        }
    }
}