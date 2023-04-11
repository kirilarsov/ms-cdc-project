package edu.kirilarsov.cdc.accountservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import edu.kirilarsov.cdc.accountservice.AbstractIT;
import edu.kirilarsov.cdc.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HealthServiceTests extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void givenHealthyDatabase_shouldReturnHealthy() {
        var healthService = new HealthService(accountRepository);

        var health = healthService.health();

        assertThat(health.database()).isTrue();
    }

}
