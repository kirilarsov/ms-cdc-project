package edu.kirilarsov.cdc.card.client;

import java.util.List;

/**
 * CardResponseDto record for response dto objects.
 */
public record CardResponseDto(List<CardDto> cards) {

    /**
     * CardDto record for response dto objects.
     */
    public record CardDto(
        String accountUuid,
        String cardHolderName,
        String pan,
        String validTo,
        String cvc,
        Status status) {

        /**
         * Currency record for response dto objects.
         */
        public enum Status {
            ACTIVE, BLOCKED, INACTIVE, IN_PROGRESS, CANCELLED, LOST, STOLEN, DAMAGED
        }
    }
}