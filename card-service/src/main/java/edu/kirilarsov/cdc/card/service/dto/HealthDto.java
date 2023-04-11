package edu.kirilarsov.cdc.card.service.dto;

/**
 * HealthDto record for dto objects.
 */
public record HealthDto(boolean database) {
    public boolean isHealthy() {
        return database();
    }
}
