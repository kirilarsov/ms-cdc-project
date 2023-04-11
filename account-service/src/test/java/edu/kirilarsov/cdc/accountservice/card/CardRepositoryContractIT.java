package edu.kirilarsov.cdc.accountservice.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.CLASSPATH;

import edu.kirilarsov.cdc.card.client.CardResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "integration.card-service-base-url=" +
            "http://localhost:${stubrunner.runningstubs.card-service.port}"
    }
)
@AutoConfigureStubRunner(
    stubsMode = CLASSPATH,
    ids = {
        "edu.kirilarsov.cdc:card-service:+:stubs"
    }
)
@ActiveProfiles({
    "test",
    "card-web-client",
    "transaction-logger-client"
})
class CardRepositoryContractIT {

    @Autowired
    private CardRepository cardRepository;

    @Test
    void testGetCard() {

        var cardDtos = cardRepository.getCardsByAccount("account-uuid-1");

        assertThat(cardDtos)
            .extracting(CardResponseDto::cards)
            .asList()
            .isNotEmpty()
            .hasSize(1)
            .element(0)
            .hasFieldOrPropertyWithValue("accountUuid", "account-uuid-1")
            .hasFieldOrPropertyWithValue("cardHolderName", "Tom Jonson")
            .hasFieldOrPropertyWithValue("pan", "1234-5678-9012-3456")
            .hasFieldOrPropertyWithValue("validTo", "11/29")
            .hasFieldOrPropertyWithValue("status", CardResponseDto.CardDto.Status.ACTIVE)
            .hasFieldOrPropertyWithValue("cvc", "123");
    }
}
