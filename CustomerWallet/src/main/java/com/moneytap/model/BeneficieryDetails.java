package com.moneytap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BeneficieryDetails {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ben_generator")
    @SequenceGenerator(name="ben_generator", initialValue = 20000)
    private Integer beneficiaryId;
    private String beneficiaryName;
    private String mobile;
    private int beneficiaryWalletId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "UserWalletId")
    private Wallet wallet;


    public BeneficieryDetails() {
    }

    public int getBeneficiaryWalletId() {
        return beneficiaryWalletId;
    }

    public void setBeneficiaryWalletId(int beneficiaryWalletId) {
        this.beneficiaryWalletId = beneficiaryWalletId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Integer getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Integer beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Override
    public String toString() {
        return "BeneficieryDetails{" +
                "beneficiaryId='" + beneficiaryId + '\'' +
                ", beneficiaryName='" + beneficiaryName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", benificiaryWalletId=" + beneficiaryWalletId +
                ", wallet=" + wallet +
                '}';
    }
}
