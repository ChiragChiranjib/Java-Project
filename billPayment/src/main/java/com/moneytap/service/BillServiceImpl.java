package com.moneytap.service;

import com.moneytap.exception.*;
import com.moneytap.model.*;
import com.moneytap.repository.BankAccountRepository;
import com.moneytap.repository.BillPaymentRepository;
import com.moneytap.repository.TransactionRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    BillPaymentRepository billPaymentRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    WalletRepository walletRepository;


    @Override
    public BillPaymentList getBills(int walletId) {
        BillPaymentList bList= new BillPaymentList();

                Wallet wallet = walletRepository.findById(walletId).get();
                List<BillPayment> list = new ArrayList<>(billPaymentRepository.findAllByWallet(wallet));
                bList.setBpList((ArrayList<BillPayment>) list);

        return bList;
    }

    @Override
    public void addBills(int walletId, BillPayment bill, int accNo) {
        bill.setWallet(walletRepository.findById(walletId).get());

        try {

            if(!bankAccountRepository.existsById(accNo))
                throw new BankAccountNotFoundException("Bank account does not exist");
            if(!bankAccountRepository.findById(accNo).get().getWallet().getWalletId().equals(walletId))
                throw new BankAccountMismatchException("Wrong account number entered!");

            bill.setBankAccount(bankAccountRepository.findById(accNo).get());
            Wallet wallet = walletRepository.findById(walletId).get();
            BankAccount bankAccount= bill.getBankAccount();

            if (bill.getBillType().equals("Pay by bank")) {
                //Update bank balance after the bill payment
                if(bankAccount.getBalance()- bill.getAmount() <= 1000)   //1000 is the minimum balance of an account
                    throw new InsufficientFundsBankException("Insufficient funds in Bank");
                bankAccount.setBalance(bankAccount.getBalance() - bill.getAmount());
                bankAccountRepository.save(bankAccount);
            } else if (bill.getBillType().equals("Pay by wallet")) {
                //Update wallet table after the bill payment
                if(wallet.getBalance()- bill.getAmount() <= 0)
                    throw new InsufficientFundsWalletException("Insufficient funds in wallet: Please recharge");
                wallet.setBalance(wallet.getBalance() - bill.getAmount());
                walletRepository.save(wallet);
            }
            else{
                throw new InvalidBillTypeException("The bill type given is invalid");
            }

            //Add the bill to the table bill payment
            billPaymentRepository.save(bill);

            //Update transaction Table
            Transaction t = new Transaction();
            Random random = new Random();
            t.setTransactionId(random.nextInt(100000));
            t.setTransactionType("Bill payment type :"+bill.getBillType());
            t.setTransactionDate(LocalDate.now());
            t.setAmount(bill.getAmount());
            t.setDescription("Bill successfully executed");
            t.setWallet(wallet);
            t.setBankAccount(bankAccount);
            transactionRepository.save(t);
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }


}
