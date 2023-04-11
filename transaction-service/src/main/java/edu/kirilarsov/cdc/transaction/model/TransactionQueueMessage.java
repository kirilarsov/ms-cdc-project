package edu.kirilarsov.cdc.transaction.model;

import java.util.List;

/**
 * Transaction Queue Message is object received on the queue that triggers transaction creation.
 */
public record TransactionQueueMessage(List<TransactionDto> transactions) {
    public TransactionQueueMessage(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionDto> transactions() {
        return this.transactions;
    }

    /**
     * Transaction Queue Message is object received on the queue that triggers transaction creation.
     */
    public static record TransactionDto(String accountUuid, String cardUuid, String merchant, String amount,
                                        String currency, TransactionDto.Type type) {

        /**
         * Transaction Queue Message is object received on the queue that triggers transaction creation.
         */
        public TransactionDto(String accountUuid, String cardUuid, String merchant, String amount, String currency,
                              TransactionDto.Type type) {
            this.accountUuid = accountUuid;
            this.cardUuid = cardUuid;
            this.merchant = merchant;
            this.amount = amount;
            this.currency = currency;
            this.type = type;
        }

        public String accountUuid() {
            return this.accountUuid;
        }

        public String cardUuid() {
            return this.cardUuid;
        }

        public String merchant() {
            return this.merchant;
        }

        public String amount() {
            return this.amount;
        }

        public String currency() {
            return this.currency;
        }

        public TransactionDto.Type type() {
            return this.type;
        }

        /**
         * Transaction Queue Message is object received on the queue that triggers transaction creation.
         */
        public static enum Type {
            AUTHORIZATION,
            SETTLEMENT,
            LOAD,
            UNLOAD,
            CHARGEBACK,
            REVERSAL,
            REFUND;

            private Type() {
            }
        }
    }
}
