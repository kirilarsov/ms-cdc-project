package edu.kirilarsov.cdc.transaction.service;

import edu.kirilarsov.cdc.transaction.repository.TransactionRepository;
import edu.kirilarsov.cdc.transaction.service.dto.HealthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * HealthService for checking service health.
 */
@Service
public class HealthService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public HealthService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
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
            transactionRepository.health();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}