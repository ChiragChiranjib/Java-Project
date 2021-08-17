package com.moneytap.service;


import com.moneytap.model.Transaction;
import com.moneytap.model.TransactionList;

import java.time.LocalDate;

public interface TransactionService {

    TransactionList getTransactions(Integer walletId);

    TransactionList getTransactionsByDate(Integer walletId, LocalDate date);

    TransactionList getTransactionsByType(Integer walletId, String type);

    TransactionList getTransactionsByAccNo(int walletId, int accNo);
}
