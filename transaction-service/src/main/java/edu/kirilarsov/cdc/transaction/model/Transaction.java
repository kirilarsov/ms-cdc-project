package edu.kirilarsov.cdc.transaction.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Transaction  model.
 */
@javax.persistence.Entity
@Table(name = "transaction")
public class Transaction extends Entity {

    /**
     * enum for defining transaction statuses.
     */
    public enum Type {
        AUTHORIZATION, SETTLEMENT, LOAD, UNLOAD, CHARGEBACK, REVERSAL, REFUND
    }

    @Column(name = "accountUuid")
    private String accountUuid;

    @Column(name = "cardUuid")
    private String cardUuid;

    @Column(name = "merchant")
    private String merchant;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;


    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCardUuid() {
        return cardUuid;
    }

    public void setCardUuid(String cardUuid) {
        this.cardUuid = cardUuid;
    }
}
