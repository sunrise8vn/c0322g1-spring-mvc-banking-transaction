package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Vui lòng nhập email")
    @Size(min = 5, max = 20, message = "Độ dài email từ 5-20 ký tự")
    @Email(message = "Vui lòng nhập đúng kiểu email")
    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String address;

    @Column(precision = 12, scale = 0, updatable = false)
    private BigDecimal balance;


    @OneToMany(mappedBy = "customer")
    private List<Deposit> deposits;

    @OneToMany(mappedBy = "customer")
    private List<Withdraw> withdraws;

    @OneToMany(mappedBy = "sender")
    private List<Transfer> senders;

    @OneToMany(mappedBy = "recipient")
    private List<Transfer> recipients;


	public Customer() {
	}

    public Customer(Long id, String fullName, String email, String phone, String address, BigDecimal balance, List<Deposit> deposits, List<Withdraw> withdraws, List<Transfer> senders, List<Transfer> recipients) {
	    this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.deposits = deposits;
        this.withdraws = withdraws;
        this.senders = senders;
        this.recipients = recipients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(List<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }

    public List<Transfer> getSenders() {
        return senders;
    }

    public void setSenders(List<Transfer> senders) {
        this.senders = senders;
    }

    public List<Transfer> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Transfer> recipients) {
        this.recipients = recipients;
    }

}
