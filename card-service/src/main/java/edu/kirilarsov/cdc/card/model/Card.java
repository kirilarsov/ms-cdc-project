package edu.kirilarsov.cdc.card.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Card  model.
 */
@javax.persistence.Entity
@Table(name = "card")
public class Card extends Entity {

    /**
     * enum for defining card statuses.
     */
    public enum Status {
        ACTIVE, BLOCKED, INACTIVE, IN_PROGRESS, CANCELLED, LOST, STOLEN, DAMAGED
    }

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "pan")
    private String pan;

    @Column(name = "validTo")
    private String validTo;

    @Column(name = "cvc")
    private String cvc;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "account_uuid")
    private String accountUuid;

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }
}
