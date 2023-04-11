package edu.kirilarsov.cdc.card.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.kirilarsov.cdc.card.AbstractWebIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("transaction-logger-client")
class HealthControllerIT extends AbstractWebIT {

    @Test
    @DisplayName("Integration test for health check testing")
    void testHealthIsOk() throws Exception {
        mockMvc.perform(get("/health"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Integration test for health check testing")
    void testHealthAuthIsOk() throws Exception {
        mockMvc.perform(get("/health/auth"))
            .andExpect(status().isOk());
    }

}
