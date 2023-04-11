package edu.kirilarsov.cdc.transaction;

import edu.kirilarsov.cdc.transaction.config.RabbitMqConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main class for bootstrapping the transactoin-service.
 */
@SpringBootApplication(scanBasePackages = "edu.kirilarsov.cdc")
@EnableTransactionManagement
@EnableConfigurationProperties({
    RabbitMqConfiguration.class
})
@EnableFeignClients
public class TransactionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }
}
