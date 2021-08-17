package com.moneytap.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class Wallet {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_generator")
    @SequenceGenerator(name="wallet_generator", initialValue = 51000)
    private int walletId;

    @JsonIgnore
    @ColumnDefault("0")
    private double balance; 


    public Wallet() {
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId='" + walletId + '\'' +
                ", balance=" + balance +
                '}';
    }
}
