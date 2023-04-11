package edu.kirilarsov.cdc.card.service;

import edu.kirilarsov.cdc.card.repository.CardRepository;
import edu.kirilarsov.cdc.card.service.dto.HealthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * HealthService for checking service health.
 */
@Service
public class HealthService {

    private final CardRepository cardRepository;

    @Autowired
    public HealthService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Checks the current health status of the service.
     *
     * @return The current health status of the service
     */
    public HealthDto health() {
        return new HealthDto(database());
    }

    private boolean database() {
        try {
            cardRepository.health();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}