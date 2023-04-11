package edu.kirilarsov.cdc.transaction.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.kirilarsov.cdc.transaction.helper.TransactionDataGenerator;
import edu.kirilarsov.cdc.transaction.mapper.TransactionDtoMapper;
import edu.kirilarsov.cdc.transaction.model.Transaction;
import edu.kirilarsov.cdc.transaction.service.TransactionService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TransactionController.class)
@ComponentScan(
    basePackageClasses = {
        TransactionDtoMapper.class
    }
)
class TransactionControllerTest {

    private static final String REQUEST_BASE_URL = "/api/transactions";

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTransactionsByAccount() throws Exception {
        List<Transaction> transactions = List.of(TransactionDataGenerator.mockTransaction());
        when(transactionService.getTransactionsByAccount("account-uuid-1")).thenReturn(
            Optional.of(transactions));

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
    void testGetTransactionsByCard() throws Exception {
        List<Transaction> transactions = List.of(TransactionDataGenerator.mockTransaction());
        when(transactionService.getTransactionsByCard("card-uuid-1")).thenReturn(
            Optional.of(transactions));

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
