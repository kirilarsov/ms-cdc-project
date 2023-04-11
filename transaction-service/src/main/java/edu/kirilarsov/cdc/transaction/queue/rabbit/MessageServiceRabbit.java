package edu.kirilarsov.cdc.transaction.queue.rabbit;

import edu.kirilarsov.cdc.transaction.config.RabbitConfig;
import edu.kirilarsov.cdc.transaction.queue.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *  Message Service Rabbit implementation.
 */
@Profile("message-rabbit-client")
@Service
public class MessageServiceRabbit implements MessageService {

    private final RabbitTemplate rabbit;

    public MessageServiceRabbit(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void send(RabbitConfig.Publisher destination, Object message) {
        rabbit.convertAndSend(destination.exchange(), destination.routingKey(), message);
    }
}
