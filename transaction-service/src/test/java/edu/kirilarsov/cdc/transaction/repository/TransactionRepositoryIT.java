package edu.kirilarsov.cdc.transaction.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.kirilarsov.cdc.transaction.AbstractIT;
import edu.kirilarsov.cdc.transaction.model.Transaction;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TransactionRepositoryIT extends AbstractIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @DatabaseSetup("classpath:dbsetup/TransactionRepository.findByAccountUuid.xml")
    void testFindByAccountUuid() {
        var transactionOptional = transactionRepository.findByAccountUuid("account-uuid-1");
        var transaction = transactionOptional.get().get(0);
        assertThat(transaction)
            .hasFieldOrPropertyWithValue("accountUuid", "account-uuid-1")
            .hasFieldOrPropertyWithValue("cardUuid", "card-uuid-1")
            .hasFieldOrPropertyWithValue("merchant", "IKEA")
            .hasFieldOrPropertyWithValue("amount", new BigDecimal("251.52"))
            .hasFieldOrPropertyWithValue("currency", "EUR")
            .hasFieldOrPropertyWithValue("type", Transaction.Type.SETTLEMENT);
    }

    @Test
    @DatabaseSetup("classpath:dbsetup/TransactionRepository.findByAccountUuid.xml")
    void testFindByCardUuid() {
        var transactionOptional = transactionRepository.findByCardUuid("card-uuid-1");
        var transaction = transactionOptional.get().get(0);
        assertThat(transaction)
            .hasFieldOrPropertyWithValue("accountUuid", "account-uuid-1")
            .hasFieldOrPropertyWithValue("cardUuid", "card-uuid-1")
            .hasFieldOrPropertyWithValue("merchant", "IKEA")
            .hasFieldOrPropertyWithValue("amount", new BigDecimal("251.52"))
            .hasFieldOrPropertyWithValue("currency", "EUR")
            .hasFieldOrPropertyWithValue("type", Transaction.Type.SETTLEMENT);
    }
}
