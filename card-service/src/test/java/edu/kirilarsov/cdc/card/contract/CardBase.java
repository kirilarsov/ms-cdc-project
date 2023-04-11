package edu.kirilarsov.cdc.card.contract;

import static org.mockito.Mockito.when;

import edu.kirilarsov.cdc.card.controller.CardController;
import edu.kirilarsov.cdc.card.helper.CardDataGenerator;
import edu.kirilarsov.cdc.card.service.CardService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@ActiveProfiles({"test", "transaction-web-client", "card-logger-client"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CardBase {

    @MockBean
    private CardService cardService;

    @Autowired
    private CardController cardController;

    @BeforeEach
    void setup() {

        when(cardService.getCardsByAccount("account-uuid-1")).thenReturn(
            Optional.of(List.of(CardDataGenerator.mockCard()))
        );
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(cardController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

}
