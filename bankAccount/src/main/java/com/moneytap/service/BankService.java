package com.moneytap.service;

import com.moneytap.model.BankAccount;
import com.moneytap.model.BankAccountList;

public interface BankService {
    BankAccountList getAccounts(int walletId);

    void addAccount(int walletId, BankAccount bankAccount);

    void deleteAccount(int walletId, int accNo);
}
