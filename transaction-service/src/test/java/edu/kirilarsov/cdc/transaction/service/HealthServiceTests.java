package edu.kirilarsov.cdc.transaction.service;

import static org.assertj.core.api.Assertions.assertThat;

import edu.kirilarsov.cdc.transaction.AbstractIT;
import edu.kirilarsov.cdc.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HealthServiceTests extends AbstractIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void givenHealthyDatabase_shouldReturnHealthy() {
        var healthService = new HealthService(transactionRepository);

        var health = healthService.health();

        assertThat(health.database()).isTrue();
    }

}
