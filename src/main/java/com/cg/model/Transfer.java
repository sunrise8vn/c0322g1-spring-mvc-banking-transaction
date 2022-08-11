package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;


@Entity
@Table(name = "transfers")
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "transfer_amount")
    private BigDecimal transferAmount;

    private float fees;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "fees_amount")
    private BigDecimal feesAmount;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Customer sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Customer recipient;

    public Transfer() {
    }

    public Transfer(Long id, @Digits(integer = 12, fraction = 0) BigDecimal transferAmount, float fees, @Digits(integer = 12, fraction = 0) BigDecimal feesAmount, @Digits(integer = 12, fraction = 0) BigDecimal transactionAmount, Customer sender, Customer recipient) {
        this.id = id;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(BigDecimal feesAmount) {
        this.feesAmount = feesAmount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }
}
