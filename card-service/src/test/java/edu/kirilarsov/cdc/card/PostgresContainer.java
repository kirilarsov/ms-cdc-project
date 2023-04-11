package edu.kirilarsov.cdc.card;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {
    public PostgresContainer() {
        super("postgres:13-alpine");
    }
}
