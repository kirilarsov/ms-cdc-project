package edu.kirilarsov.cdc.accountservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.kirilarsov.cdc.accountservice.AbstractWebIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountControllerIT extends AbstractWebIT {

    private static final String REQUEST_BASE_URL = "/api/accounts";

    @Test
    @DatabaseSetup("classpath:dbsetup/AccountController.getAccount.xml")
    @DisplayName("Integration test for getting accounts with userUuid")
    void testGetAccount() throws Exception {
        mockMvc.perform(get(REQUEST_BASE_URL)
                .queryParam("userUuid", "f7b38fd1-025a-46a5-8f6e-371a2343f557"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accounts").isArray())
            .andExpect(jsonPath("$.accounts[0].balance").value("750.25"))
            .andExpect(jsonPath("$.accounts[0].currency").value("EUR"));
    }

}
