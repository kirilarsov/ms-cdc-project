package edu.kirilarsov.cdc.transaction.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

/**
 * AmqpConfiguration.
 */
@Configuration
public class AmqpConfiguration {

    private final RabbitMqConfiguration configuration;

    public AmqpConfiguration(RabbitMqConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Declarables.
     *
     * @return Declarables
     */
    @Profile("!test")
    @Bean
    public Declarables declarables() {
        var declarables = new ArrayList<Declarable>();

        var resourcesByExchange = configuration.resources().stream()
            .collect(Collectors.groupingBy(RabbitConfig::exchange));

        for (var entry : resourcesByExchange.entrySet()) {
            var exchange = ExchangeBuilder.topicExchange(entry.getKey())
                .durable(true)
                .build();

            declarables.add(exchange);

            for (var resource : entry.getValue()) {
                if (resource instanceof RabbitConfig.Listener listener) {
                    var queue = QueueBuilder.durable(listener.queue())
                        .build();

                    declarables.add(queue);

                    var binding = BindingBuilder.bind(queue)
                        .to(exchange)
                        .with(listener.routingKey())
                        .noargs();

                    declarables.add(binding);
                }
            }
        }

        return new Declarables(declarables);
    }

    /**
     * MessageHandlerMethodFactory.
     *
     * @return MessageHandlerMethodFactory
     *
     */
    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        var factory = new DefaultMessageHandlerMethodFactory();

        var jsonConverter = new MappingJackson2MessageConverter();

        jsonConverter.getObjectMapper()
            .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
            .registerModule(new JavaTimeModule())
            .registerModule(new Jdk8Module());

        factory.setMessageConverter(jsonConverter);
        return factory;
    }

    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
        final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return c -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }

    /**
     * Converter.
     *
     * @param builder Jackson2ObjectMapperBuilder
     *
     * @return Jackson2JsonMessageConverter
     *
     */
    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        var objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
