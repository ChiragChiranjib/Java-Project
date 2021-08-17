package com.moneytap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class BankAccount {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_generator")
    @SequenceGenerator(name="bank_generator", initialValue = 1230000)
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
