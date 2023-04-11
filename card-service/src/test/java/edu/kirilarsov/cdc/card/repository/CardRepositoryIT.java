package edu.kirilarsov.cdc.card.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.kirilarsov.cdc.card.AbstractIT;
import edu.kirilarsov.cdc.card.model.Card;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CardRepositoryIT extends AbstractIT {

    @Autowired
    private CardRepository cardRepository;

    @Test
    @DatabaseSetup("classpath:dbsetup/CardRepository.findByAccountUuid.xml")
    void testFindById() {
        var cardOptional = cardRepository.findByAccountUuid("account-uuid-1");
        var card = cardOptional.get().get(0);
        assertThat(card)
            .hasFieldOrPropertyWithValue("cardHolderName", "Tom Jonson")
            .hasFieldOrPropertyWithValue("pan", "1234-5678-9012-3456")
            .hasFieldOrPropertyWithValue("validTo", "11/29")
            .hasFieldOrPropertyWithValue("cvc", "123")
            .hasFieldOrPropertyWithValue("accountUuid", "account-uuid-1");
    }

}
