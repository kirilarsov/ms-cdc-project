package edu.kirilarsov.cdc.accountservice.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.kirilarsov.cdc.accountservice.helper.AccountDataGenerator;
import edu.kirilarsov.cdc.accountservice.model.Account;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AccountDtoMapperTest {

    AccountDtoMapper accountDtoMapper = Mappers.getMapper(AccountDtoMapper.class);

    @Test
    void testMapToAccountDto() {
        Account account = AccountDataGenerator.mockAccount();
        assertThat(accountDtoMapper.mapToAccountDto(account))
            .isNotNull()
            .hasFieldOrPropertyWithValue("balance", new BigDecimal("750.25"))
            .hasFieldOrPropertyWithValue("name","Debit Account")
            .hasFieldOrPropertyWithValue("userUuid","a6d2c821-c730-43ae-af78-992c1e1e6bb2");
    }
}
