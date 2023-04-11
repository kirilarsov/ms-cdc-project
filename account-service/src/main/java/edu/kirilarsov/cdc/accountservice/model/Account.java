package edu.kirilarsov.cdc.accountservice.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Account  model.
 */
@javax.persistence.Entity
@Table(name = "account")
public class Account extends Entity {

    /**
     * enum for defining account currencies.
     */
    public enum Currency {
        EUR, GBP
    }

    @Column(name = "name")
    private String name;


    @Column(name = "balance")

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Account.Currency currency;

    @Column(name = "user_uuid")
    private String userUuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
