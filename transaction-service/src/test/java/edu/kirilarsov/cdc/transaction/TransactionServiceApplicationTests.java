package edu.kirilarsov.cdc.transaction;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.kirilarsov.cdc.transaction.controller.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceApplicationTests {

    @Autowired
    private TransactionController transactionController;

    @Test
    void contextLoads() {
        assertThat(this.transactionController).isNotNull();
    }

}
