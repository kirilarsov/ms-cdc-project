package edu.kirilarsov.cdc.transaction.config;

/**
 * RabbitConfig.
 */
public interface RabbitConfig {
    String exchange();

    String routingKey();

    /**
     * Listener.
     */
    interface Listener extends RabbitConfig {
        String queue();
    }

    /**
     * Publisher.
     */
    interface Publisher extends RabbitConfig {
    }
}
