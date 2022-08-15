package com.cg.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;


@Entity
@Table(name = "deposits")
public class Deposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "100", message = "Số tiền tối thiểu là 100")
    @DecimalMax(value = "10000", message = "Số tiền tối đa là 10.000")
    @Column(name = "transaction_amount", precision = 12, scale = 0)
    private BigDecimal transactionAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Deposit() {
    }

    public Deposit(Long id, BigDecimal transactionAmount, Customer customer) {
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
