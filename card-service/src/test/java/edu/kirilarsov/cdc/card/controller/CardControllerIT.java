package edu.kirilarsov.cdc.card.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.kirilarsov.cdc.card.AbstractWebIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("transaction-logger-client")
class CardControllerIT extends AbstractWebIT {

    private static final String REQUEST_BASE_URL = "/api/cards";

    @Test
    @DatabaseSetup("classpath:dbsetup/CardController.getCards.xml")
    @DisplayName("Integration test for getting cards with accountUuid")
    void testGetCard() throws Exception {
        mockMvc.perform(get(REQUEST_BASE_URL)
                .queryParam("accountUuid", "account-uuid-1"))
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
