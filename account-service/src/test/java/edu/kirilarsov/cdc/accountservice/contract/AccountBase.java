package edu.kirilarsov.cdc.accountservice.contract;

import static org.mockito.Mockito.when;

import edu.kirilarsov.cdc.accountservice.controller.AccountController;
import edu.kirilarsov.cdc.accountservice.helper.AccountDataGenerator;
import edu.kirilarsov.cdc.accountservice.service.AccountService;
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

@ActiveProfiles({
    "test",
    "card-web-client",
    "transaction-web-client"
})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AccountBase {

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountController accountController;

    @BeforeEach
    void setup() {

        when(accountService.getAccountsByUser("a6d2c821-c730-43ae-af78-992c1e1e6bb2")).thenReturn(
            Optional.of(List.of(AccountDataGenerator.mockAccount()))
        );
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(accountController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

}
