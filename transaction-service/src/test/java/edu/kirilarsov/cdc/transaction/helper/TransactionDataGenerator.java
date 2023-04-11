package edu.kirilarsov.cdc.transaction.helper;

import edu.kirilarsov.cdc.transaction.model.Transaction;
import edu.kirilarsov.cdc.transaction.model.TransactionQueueMessage;
import java.math.BigDecimal;
import java.util.List;

public class TransactionDataGenerator {

    public static Transaction mockTransaction() {
        var transaction = new Transaction();
        transaction.setAccountUuid("account-uuid-1");
        transaction.setType(Transaction.Type.SETTLEMENT);
        transaction.setCardUuid("card-uuid-1");
        transaction.setAccountUuid("account-uuid-1");
        transaction.setMerchant("IKEA");
        transaction.setAmount(new BigDecimal("251.52"));
        transaction.setCurrency("EUR");
        return transaction;
    }

    public static TransactionQueueMessage mockTransactionQueueMessage() {
        return new TransactionQueueMessage(List.of(
            new TransactionQueueMessage.TransactionDto(
                "account-uuid-1",
                "card-uuid-1",
                "IKEA",
                "251.52",
                "EUR",
                TransactionQueueMessage.TransactionDto.Type.SETTLEMENT
            )
        ));
    }

}
