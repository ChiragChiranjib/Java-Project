package com.moneytap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Transaction implements Serializable {

    @Id
    @JsonIgnore
    private int transactionId;
    private String transactionType;
    private LocalDate transactionDate;
    private double amount;
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accNo")
    private BankAccount bankAccount;

    public Transaction() {
    }

    public Transaction(int transactionId, String transactionType, LocalDate transactionDate, double amount, String description, Wallet wallet, BankAccount bankAccount) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
        this.wallet = wallet;
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
