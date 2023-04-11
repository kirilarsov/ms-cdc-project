package edu.kirilarsov.cdc.accountservice.service;

import edu.kirilarsov.cdc.accountservice.repository.AccountRepository;
import edu.kirilarsov.cdc.accountservice.service.dto.HealthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * HealthService for checking service health.
 */
@Service
public class HealthService {

    private final AccountRepository accountRepository;

    @Autowired
    public HealthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
            accountRepository.health();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}