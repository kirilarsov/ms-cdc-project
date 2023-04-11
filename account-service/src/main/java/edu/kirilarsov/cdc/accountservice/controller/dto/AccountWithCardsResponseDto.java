package edu.kirilarsov.cdc.accountservice.controller.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * AccountResponseDto record for response dto objects.
 */
public record AccountWithCardsResponseDto(List<AccountDto> accounts) {

    /**
     * AccountDto record for response dto objects.
     */
    public record AccountDto(
        String userUuid,
        String name,
        BigDecimal balance,
        Currency currency,
        List<CardDto> cards) {

        /**
         * Currency record for response dto objects.
         */
        public enum Currency {
            EUR, GBP
        }

        /**
         * CardDto record for response dto objects.
         */
        public record CardDto(
            String accountUuid,
            String cardHolderName,
            String pan,
            String validTo,
            String cvc,
            CardDto.Status status) {

            /**
             * Currency record for response dto objects.
             */
            public enum Status {
                ACTIVE, BLOCKED, INACTIVE, IN_PROGRESS, CANCELLED, LOST, STOLEN, DAMAGED
            }
        }
    }
}