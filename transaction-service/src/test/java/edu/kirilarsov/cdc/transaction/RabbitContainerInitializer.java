package edu.kirilarsov.cdc.transaction;

import java.util.Map;
import java.util.stream.Stream;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.lifecycle.Startables;

public class RabbitContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.8-management-alpine");

    private static void startContainers() {
        Startables.deepStart(Stream.of(rabbitMQContainer)).join();
    }

    private static Map<String, Object> createConnectionConfiguration() {
        return Map.of(
            "spring.rabbitmq.username", rabbitMQContainer.getAdminUsername(),
            "spring.rabbitmq.password", rabbitMQContainer.getAdminPassword(),
            "spring.rabbitmq.addresses", rabbitMQContainer.getAmqpUrl()
        );
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        startContainers();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MapPropertySource testcontainers = new MapPropertySource(
            "testcontainers",
             createConnectionConfiguration()
        );
        environment.getPropertySources().addFirst(testcontainers);
    }
}

