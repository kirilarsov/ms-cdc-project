package edu.kirilarsov.cdc.transaction.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * RabbitMqConfiguration.
 */
@ConfigurationProperties("transaction-service.rabbitmq")
@ConstructorBinding
public record RabbitMqConfiguration(Inputs inputs, Outputs outputs) {

    /**
     * resources.
     *
     * @return List
     */
    public List<RabbitConfig> resources() {
        return List.of(inputs.processFile, outputs.fileProcessed, outputs.transactionError);
    }

    /**
     * All incoming messages.
     */
    public record Inputs(Input processFile) {

        /**
         * Incoming contract.message definition.
         */
        public record Input(String exchange, String queue, String routingKey)
            implements RabbitConfig.Listener {
        }
    }

    /**
     * All outgoing messages.
     */
    public record Outputs(Output fileProcessed, Output transactionError) {

        /**
         * Outgoing  contract.message definition.
         */
        public record Output(String exchange, String routingKey)
            implements RabbitConfig.Publisher {
        }
    }
}
