package edu.kirilarsov.cdc.transaction.contract;

import static org.mockito.Mockito.when;

import edu.kirilarsov.cdc.transaction.controller.TransactionController;
import edu.kirilarsov.cdc.transaction.helper.TransactionDataGenerator;
import edu.kirilarsov.cdc.transaction.service.TransactionService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@ActiveProfiles({"test","message-logger-client"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CardBase {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private TransactionController transactionController;

    @BeforeEach
    void setup() {

        when(transactionService.getTransactionsByCard("card-uuid-1")).thenReturn(
            Optional.of(List.of(TransactionDataGenerator.mockTransaction()))
        );
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(transactionController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

}
