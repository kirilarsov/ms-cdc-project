package edu.kirilarsov.cdc.accountservice.helper;

import edu.kirilarsov.cdc.accountservice.model.Account;
import java.math.BigDecimal;

public class AccountDataGenerator {

    public static Account mockAccount() {
        var account = new Account();
        account.setBalance(new BigDecimal("750.25"));
        account.setCurrency(Account.Currency.EUR);
        account.setName("Debit Account");
        account.setUserUuid("a6d2c821-c730-43ae-af78-992c1e1e6bb2");
        return account;
    }


}
