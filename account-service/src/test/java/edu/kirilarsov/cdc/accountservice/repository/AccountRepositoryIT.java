package edu.kirilarsov.cdc.accountservice.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.kirilarsov.cdc.accountservice.AbstractIT;
import edu.kirilarsov.cdc.accountservice.model.Account;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AccountRepositoryIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DatabaseSetup("classpath:dbsetup/AccountRepository.findByUserUuid.xml")
    void testFindById() {
        var accountOptional = accountRepository.findByUserUuid("user-uuid-1");
        var account = accountOptional.get().get(0);
        assertThat(account)
            .hasFieldOrPropertyWithValue("name", "Wayne Corp. Ltd")
            .hasFieldOrPropertyWithValue("balance", new BigDecimal("750.25"))
            .hasFieldOrPropertyWithValue("currency", Account.Currency.EUR)
            .hasFieldOrPropertyWithValue("userUuid", "user-uuid-1");
    }

}
