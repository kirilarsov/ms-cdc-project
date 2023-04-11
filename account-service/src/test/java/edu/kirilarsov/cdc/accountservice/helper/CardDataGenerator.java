package edu.kirilarsov.cdc.accountservice.helper;


import edu.kirilarsov.cdc.card.client.CardResponseDto;
import java.util.List;

public class CardDataGenerator {

    public static CardResponseDto mockCardResponseDto() {
        return new CardResponseDto(
            List.of(new CardResponseDto.CardDto(
                "account-uuid-1",
                "Tom Jonson",
                "1234-5678-9012-3456",
                "11/29",
                "123",
                CardResponseDto.CardDto.Status.ACTIVE
            )));
    }


}
