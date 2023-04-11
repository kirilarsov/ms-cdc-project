package edu.kirilarsov.cdc.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.kirilarsov.cdc.card.controller.CardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("transaction-logger-client")
class CardsServiceApplicationTests {

    @Autowired
    private CardController cardController;

    @Test
    void contextLoads() {
        assertThat(this.cardController).isNotNull();
    }

}
