package edu.kirilarsov.cdc.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main class for bootstrapping the account-service.
 */
@SpringBootApplication(scanBasePackages = "edu.kirilarsov.cdc")
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableFeignClients(basePackages = "edu.kirilarsov.cdc")
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
