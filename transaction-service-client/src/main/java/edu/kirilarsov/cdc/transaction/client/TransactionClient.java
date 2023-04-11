package edu.kirilarsov.cdc.transaction.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "transaction-service",
    url = "${integration.transaction-service-base-url}/api"
)
public interface TransactionClient {
    @GetMapping(value = "/transactions/account/{accountUuid}", consumes = APPLICATION_JSON_VALUE)
    TransactionResponseDto getTransactionsByAccount(@PathVariable String accountUuid);

    @GetMapping(value = "/transactions/card/{cardUuid}", consumes = APPLICATION_JSON_VALUE)
    TransactionResponseDto getTransactionsByCard(@PathVariable String cardUuid);
}
