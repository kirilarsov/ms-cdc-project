package edu.kirilarsov.cdc.card.helper;

import edu.kirilarsov.cdc.card.model.Card;
import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import java.util.List;

public class CardDataGenerator {

    public static Card mockCard() {
        var card = new Card();
        card.setCardHolderName("Tom Jonson");
        card.setStatus(Card.Status.ACTIVE);
        card.setCvc("123");
        card.setAccountUuid("account-uuid-1");
        card.setPan("1234-5678-9012-3456");
        card.setValidTo("11/29");
        return card;
    }

    public static TransactionResponseDto mockCardTransactions() {
        return new TransactionResponseDto(List.of(
            new TransactionResponseDto.TransactionDto(
                "account-uuid-1",
                "card-uuid-1",
                "IKEA",
                "251.52",
                "EUR",
                TransactionResponseDto.TransactionDto.Type.SETTLEMENT
            )
        ));
    }

}
