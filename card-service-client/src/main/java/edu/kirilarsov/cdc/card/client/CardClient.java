package edu.kirilarsov.cdc.card.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "card-service",
    url = "${integration.card-service-base-url}/api"
)
public interface CardClient {
    @GetMapping(value = "/cards", consumes = APPLICATION_JSON_VALUE)
    CardResponseDto getCardsByAccount(@RequestParam @NotBlank String accountUuid);
}
