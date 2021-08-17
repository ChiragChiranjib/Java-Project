package com.moneytap.service;

import com.moneytap.exception.BankAccountNotFoundException;
import com.moneytap.exception.WalletIdMismatchException;
import com.moneytap.model.BankAccount;
import com.moneytap.model.BankAccountList;
import com.moneytap.model.Wallet;
import com.moneytap.repository.BankAccountRepository;
import com.moneytap.repository.BillPaymentRepository;
import com.moneytap.repository.TransactionRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class bankServiceImpl implements BankService{

    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BillPaymentRepository billPaymentRepository;

    @Override
    public BankAccountList getAccounts(int walletId) {
        BankAccountList baList = new BankAccountList();

                Wallet wallet = walletRepository.findById(walletId).get();

                List<BankAccount> list = new ArrayList<>(bankAccountRepository.findAllByWallet(wallet));
                baList.setBaList((ArrayList<BankAccount>) list);

        return baList;
    }

    @Override
    public void addAccount(int walletId, BankAccount bankAccount) {
        bankAccount.setWallet(walletRepository.findById(walletId).get());
              bankAccountRepository.save(bankAccount);
    }


    @Override
    public void deleteAccount(int walletId,int accNo){

        try {
            if (bankAccountRepository.existsById(accNo)) {    //if bank account exists then wallet Id exists (table constraints)
                BankAccount bankAccount= bankAccountRepository.findById(accNo).get();
                if(!bankAccount.getWallet().getWalletId().equals(walletId))
                    throw new WalletIdMismatchException("Bank account number does not corresponding to wallet Id");

                // Clearing up foreign table constraints
                transactionRepository.deleteAllByBankAccount(bankAccount);
                billPaymentRepository.deleteAllByBankAccount(bankAccount);

                bankAccountRepository.deleteById(accNo);
            } else {
                throw new BankAccountNotFoundException("Bank account to be deleted not found.");
            }
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }


}
