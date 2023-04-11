package edu.kirilarsov.cdc.card.service;

import static org.assertj.core.api.Assertions.assertThat;

import edu.kirilarsov.cdc.card.AbstractIT;
import edu.kirilarsov.cdc.card.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HealthServiceTests extends AbstractIT {

    @Autowired
    private CardRepository cardRepository;

    @Test
    void givenHealthyDatabase_shouldReturnHealthy() {
        var healthService = new HealthService(cardRepository);

        var health = healthService.health();

        assertThat(health.database()).isTrue();
    }

}
