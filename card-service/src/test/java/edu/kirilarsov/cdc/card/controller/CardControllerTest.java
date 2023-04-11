package edu.kirilarsov.cdc.card.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.kirilarsov.cdc.card.helper.CardDataGenerator;
import edu.kirilarsov.cdc.card.mapper.CardDtoMapper;
import edu.kirilarsov.cdc.card.model.Card;
import edu.kirilarsov.cdc.card.service.CardService;
import edu.kirilarsov.cdc.card.transaction.TransactionService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CardController.class)
@ComponentScan(
    basePackageClasses = {
        CardDtoMapper.class
    }
)
class CardControllerTest {

    private static final String REQUEST_BASE_URL = "/api/cards";

    @MockBean
    private CardService cardService;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCard() throws Exception {
        List<Card> cards = List.of(CardDataGenerator.mockCard());
        when(cardService.getCardsByAccount("account-uuid-1")).thenReturn(
            Optional.of(cards));

        mockMvc.perform(get(REQUEST_BASE_URL + "?accountUuid=account-uuid-1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cards").isArray())
            .andExpect(jsonPath("$.cards[0].cardHolderName").value("Tom Jonson"))
            .andExpect(jsonPath("$.cards[0].pan").value("1234-5678-9012-3456"))
            .andExpect(jsonPath("$.cards[0].validTo").value("11/29"))
            .andExpect(jsonPath("$.cards[0].cvc").value("123"))
            .andExpect(jsonPath("$.cards[0].accountUuid").value("account-uuid-1"))
            .andExpect(jsonPath("$.cards[0].status").value("ACTIVE"));
    }

}
