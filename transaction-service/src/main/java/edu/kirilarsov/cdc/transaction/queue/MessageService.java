package edu.kirilarsov.cdc.transaction.queue;


import edu.kirilarsov.cdc.transaction.config.RabbitConfig;

/**
 * Sending contract.message on queue.
 *
 */
public interface MessageService {
    void send(RabbitConfig.Publisher destination, Object message);
}

