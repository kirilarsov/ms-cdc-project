package edu.kirilarsov.cdc.card.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.kirilarsov.cdc.card.helper.CardDataGenerator;
import edu.kirilarsov.cdc.card.model.Card;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CardDtoMapperTest {

    CardDtoMapper cardDtoMapper = Mappers.getMapper(CardDtoMapper.class);

    @Test
    void testMapToCardDto() {
        Card card = CardDataGenerator.mockCard();
        assertThat(cardDtoMapper.mapToCardDto(card))
            .isNotNull()
            .hasFieldOrPropertyWithValue("cardHolderName", "Tom Jonson")
            .hasFieldOrPropertyWithValue("pan", "1234-5678-9012-3456")
            .hasFieldOrPropertyWithValue("validTo", "11/29")
            .hasFieldOrPropertyWithValue("cvc", "123")
            .hasFieldOrPropertyWithValue("accountUuid", "account-uuid-1");
    }
}
