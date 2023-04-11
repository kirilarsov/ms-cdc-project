package edu.kirilarsov.cdc.accountservice.controller.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * AccountResponseDto record for response dto objects.
 */
public record AccountResponseDto(List<AccountDto> accounts) {

    /**
     * AccountDto record for response dto objects.
     */
    public record AccountDto(
        String userUuid,
        String name,
        BigDecimal balance,
        Currency currency) {

        /**
         * Currency record for response dto objects.
         */
        public enum Currency {
            EUR, GBP
        }
    }
}