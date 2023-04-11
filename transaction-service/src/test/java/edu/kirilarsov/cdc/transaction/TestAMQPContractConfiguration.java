package edu.kirilarsov.cdc.transaction;

import org.springframework.amqp.core.Binding;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestAMQPContractConfiguration {

    @Bean
    public Binding transactionReadBinding() {
        return new Binding("notificationQueue", Binding.DestinationType.QUEUE,
            "processor.topic", "processor.event.notification", null);
    }

    @Bean
    public Binding transactionSendBinding() {
        return new Binding("transactionQueue", Binding.DestinationType.QUEUE,
            "processor.topic", "transaction.event.created", null);
    }



}
