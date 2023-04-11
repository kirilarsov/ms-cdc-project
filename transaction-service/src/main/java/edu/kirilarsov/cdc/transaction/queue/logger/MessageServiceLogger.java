package edu.kirilarsov.cdc.transaction.queue.logger;

import edu.kirilarsov.cdc.transaction.config.RabbitConfig;
import edu.kirilarsov.cdc.transaction.queue.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * MessageServiceLogger implementation.
 */
@Profile("message-logger-client")
@Service
public class MessageServiceLogger implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceLogger.class);

    @Override
    public void send(RabbitConfig.Publisher destination, Object message) {
        logger.info("Method invoked: send() from MessageServiceLogger");
    }
}
