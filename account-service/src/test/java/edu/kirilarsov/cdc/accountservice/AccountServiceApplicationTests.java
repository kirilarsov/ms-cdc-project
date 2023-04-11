package edu.kirilarsov.cdc.accountservice;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.kirilarsov.cdc.accountservice.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({
    "card-web-client",
    "transaction-web-client"
})
class AccountServiceApplicationTests {

    @Autowired
    private AccountController accountController;

    @Test
    void contextLoads() {
        assertThat(this.accountController).isNotNull();
    }

}
