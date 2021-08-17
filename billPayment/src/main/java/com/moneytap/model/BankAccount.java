package com.moneytap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount {

    @Id
    private int accNo;
    private String IFSCode;
    private String bankName;
    private double balance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    public BankAccount() {
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Integer getAccNo() {
        return accNo;
    }

    public void setAccNo(Integer accNo) {
        this.accNo = accNo;
    }

    public String getIFSCode() {
        return IFSCode;
    }

    public void setIFSCode(String IFSCode) {
        this.IFSCode = IFSCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accNo='" + accNo + '\'' +
                ", IFSCode='" + IFSCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
