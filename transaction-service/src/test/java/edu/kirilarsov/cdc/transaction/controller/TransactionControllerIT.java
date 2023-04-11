package edu.kirilarsov.cdc.transaction.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.kirilarsov.cdc.transaction.AbstractWebIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TransactionControllerIT extends AbstractWebIT {

    private static final String REQUEST_BASE_URL = "/api/transactions";

    @Test
    @DatabaseSetup("classpath:dbsetup/TransactionController.getTransactions.xml")
    @DisplayName("Integration test for getting transactions with accountUuid")
    void testGetTransactionsByAccount() throws Exception {
        mockMvc.perform(get(REQUEST_BASE_URL + "/account/account-uuid-1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.transactions").isArray())
            .andExpect(jsonPath("$.transactions[0].accountUuid").value("account-uuid-1"))
            .andExpect(jsonPath("$.transactions[0].cardUuid").value("card-uuid-1"))
            .andExpect(jsonPath("$.transactions[0].merchant").value("IKEA"))
            .andExpect(jsonPath("$.transactions[0].amount").value("251.52"))
            .andExpect(jsonPath("$.transactions[0].currency").value("EUR"))
            .andExpect(jsonPath("$.transactions[0].type").value("SETTLEMENT"));
    }

    @Test
    @DatabaseSetup("classpath:dbsetup/TransactionController.getTransactions.xml")
    @DisplayName("Integration test for getting transactions with cardUuid")
    void testGetTransactionsByCard() throws Exception {
        mockMvc.perform(get(REQUEST_BASE_URL + "/card/card-uuid-1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.transactions").isArray())
            .andExpect(jsonPath("$.transactions[0].accountUuid").value("account-uuid-1"))
            .andExpect(jsonPath("$.transactions[0].cardUuid").value("card-uuid-1"))
            .andExpect(jsonPath("$.transactions[0].merchant").value("IKEA"))
            .andExpect(jsonPath("$.transactions[0].amount").value("251.52"))
            .andExpect(jsonPath("$.transactions[0].currency").value("EUR"))
            .andExpect(jsonPath("$.transactions[0].type").value("SETTLEMENT"));
    }

}
