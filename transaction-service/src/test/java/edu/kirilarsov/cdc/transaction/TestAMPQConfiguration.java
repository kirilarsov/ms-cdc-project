package edu.kirilarsov.cdc.transaction;

import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "message-rabbit-client"})
@TestConfiguration
public class TestAMPQConfiguration {

    @Bean
    public Declarables testDeclarables() {
        var declarables = new ArrayList<Declarable>();
        var exchange = ExchangeBuilder.topicExchange("processor.topic")
            .durable(true)
            .build();
        declarables.add(exchange);
        var queue = QueueBuilder.durable("notificationQueue").build();
        declarables.add(queue);
        var binding = BindingBuilder.bind(queue)
            .to(exchange)
            .with("processor.event.notification")
            .noargs();
        declarables.add(binding);

        queue = QueueBuilder.durable("errorNotification").build();
        declarables.add(queue);
        binding = BindingBuilder.bind(queue)
            .to(exchange)
            .with("processor.event.error.notification")
            .noargs();
        declarables.add(binding);


        exchange = ExchangeBuilder.topicExchange("processor.topic")
            .durable(true)
            .build();
        declarables.add(exchange);
        queue = QueueBuilder.durable("transactionQueue").build();
        declarables.add(queue);
        binding = BindingBuilder.bind(queue)
            .to(exchange)
            .with("processor.event.created")
            .noargs();
        declarables.add(binding);

        return new Declarables(declarables);
    }



}
