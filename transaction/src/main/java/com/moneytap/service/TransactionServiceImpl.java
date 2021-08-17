package com.moneytap.service;


import com.moneytap.exception.BankAccountNotFoundException;
import com.moneytap.exception.WalletIdMismatchException;
import com.moneytap.model.BankAccount;
import com.moneytap.model.Transaction;
import com.moneytap.model.TransactionList;
import com.moneytap.model.Wallet;
import com.moneytap.repository.BankAccountRepository;
import com.moneytap.repository.TransactionRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public TransactionList getTransactions(Integer walletId) {
        TransactionList tList= new TransactionList();
          Wallet wallet = walletRepository.findById(walletId).get();
          List<Transaction> list = new ArrayList<>(transactionRepository.findAllByWallet(wallet));
          tList.settList((ArrayList<Transaction>) list);
        return tList;
    }

    @Override
    public TransactionList getTransactionsByDate(Integer walletId, LocalDate date) {
        TransactionList tList= new TransactionList();

                Wallet wallet = walletRepository.findById(walletId).get();
                List<Transaction> list = new ArrayList<>(transactionRepository.findAllByWallet(wallet));
                ArrayList<Transaction> transactions = (ArrayList<Transaction>) list.stream().filter(T -> T.getTransactionDate().equals(date)).collect(Collectors.toList());
                tList.settList(transactions);

        return tList;
    }

    @Override
    public TransactionList getTransactionsByType(Integer walletId, String type) {
        TransactionList tList= new TransactionList();

                Wallet wallet = walletRepository.findById(walletId).get();
                List<Transaction> list = new ArrayList<>(transactionRepository.findAllByWallet(wallet));
                ArrayList<Transaction> transactions = (ArrayList<Transaction>) list.stream().filter(T -> T.getTransactionType().equals(type)).collect(Collectors.toList());
                tList.settList(transactions);

        return tList;
    }

    @Override
    public TransactionList getTransactionsByAccNo(int walletId, int accNo) {
        TransactionList tList= new TransactionList();

        try {
            if(bankAccountRepository.existsById(accNo)) {                     //if exists , wallet Id also exists (Table constraint)
                if(!bankAccountRepository.findById(accNo).get().getWallet().getWalletId().equals(walletId))
                    throw new WalletIdMismatchException("Bank account does not belong to wallet ID");
            BankAccount bankAccount = bankAccountRepository.findById(accNo).get();
            List<Transaction> list = new ArrayList<>(transactionRepository.findAllByBankAccount(bankAccount));
            tList.settList((ArrayList<Transaction>) list);
            }
            else
                throw new BankAccountNotFoundException("Bank account not found");
        }
        catch (Exception E){
            E.printStackTrace();
        }
        return tList;
    }


}
