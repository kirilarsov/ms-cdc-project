package edu.kirilarsov.cdc.accountservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.kirilarsov.cdc.accountservice.card.CardService;
import edu.kirilarsov.cdc.accountservice.helper.AccountDataGenerator;
import edu.kirilarsov.cdc.accountservice.mapper.AccountDtoMapper;
import edu.kirilarsov.cdc.accountservice.model.Account;
import edu.kirilarsov.cdc.accountservice.service.AccountService;
import edu.kirilarsov.cdc.accountservice.transaction.TransactionService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountController.class)
@ComponentScan(
    basePackageClasses = {
        AccountDtoMapper.class,
        CardService.class,
        TransactionService.class
    }
)
@ActiveProfiles({"test", "card-logger-client","transaction-logger-client"})
class AccountControllerTest {

    private static final String REQUEST_BASE_URL = "/api/accounts";

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAccount() throws Exception {
        List<Account> accounts = List.of(AccountDataGenerator.mockAccount());
        when(accountService.getAccountsByUser("a6d2c821-c730-43ae-af78-992c1e1e6bb2")).thenReturn(
            Optional.of(accounts));

        mockMvc.perform(get(REQUEST_BASE_URL + "?userUuid=a6d2c821-c730-43ae-af78-992c1e1e6bb2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accounts").isArray())
            .andExpect(jsonPath("$.accounts[0].balance").value("750.25"))
            .andExpect(jsonPath("$.accounts[0].currency").value("EUR"));
    }

}
