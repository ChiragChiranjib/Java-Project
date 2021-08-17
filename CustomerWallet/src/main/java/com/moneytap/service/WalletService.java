package com.moneytap.service;

import com.moneytap.model.Customer;

import java.util.ArrayList;

public interface WalletService {
    ArrayList<Customer> getCustomers();

    double getBalance(String wId);

    void addMoney(Double money,String wId, Integer accNo);

    void depositMoney(Double money, String wId, Integer accNo);

    void fundTransfer(Double money, Integer walletId ,String benId, Integer accNo, Integer benAccNo);

    void changePassword(Integer walletId,String password);

    String logout();
}
