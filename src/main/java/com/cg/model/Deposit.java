package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;


@Entity
@Table(name = "deposits")
public class Deposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Deposit() {
    }

    public Deposit(Long id, @Digits(integer = 12, fraction = 0) BigDecimal transactionAmount, Customer customer) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
