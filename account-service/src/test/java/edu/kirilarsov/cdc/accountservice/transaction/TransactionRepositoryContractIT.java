package edu.kirilarsov.cdc.accountservice.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.CLASSPATH;

import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "integration.transaction-service-base-url=" +
            "http://localhost:${stubrunner.runningstubs.transaction-service.port}"
    }
)
@AutoConfigureStubRunner(
    stubsMode = CLASSPATH,
    ids = {
        "edu.kirilarsov.cdc:transaction-service:+:stubs"
    }
)
@ActiveProfiles({
    "test",
    "card-logger-client",
    "transaction-web-client"
})
class TransactionRepositoryContractIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testGetTransaction() {

        var transactionDtos = transactionRepository.getTransactionsByAccount("account-uuid-1");

        assertThat(transactionDtos)
            .extracting(TransactionResponseDto::transactions)
            .asList()
            .isNotEmpty()
            .hasSize(1)
            .element(0)
            .hasFieldOrPropertyWithValue("accountUuid", "account-uuid-1")
            .hasFieldOrPropertyWithValue("cardUuid", "card-uuid-1")
            .hasFieldOrPropertyWithValue("merchant", "IKEA")
            .hasFieldOrPropertyWithValue("amount", "251.52")
            .hasFieldOrPropertyWithValue("currency", "EUR")
            .hasFieldOrPropertyWithValue("type", TransactionResponseDto.TransactionDto.Type.SETTLEMENT);
    }
}
