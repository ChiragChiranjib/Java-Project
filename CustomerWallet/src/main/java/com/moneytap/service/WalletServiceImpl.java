package com.moneytap.service;

import com.moneytap.exception.*;
import com.moneytap.model.*;
import com.moneytap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Service
public class WalletServiceImpl implements WalletService {


    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BeneficiaryRepository beneficiaryRepository;
    @Autowired
    JwtRepository jwtRepository;

    @Override
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> cList= new ArrayList<>();
        customerRepository.findAll().forEach(cList::add);
        return cList;
    }

    @Override
    public double getBalance(String walletId) {

        return walletRepository.findById(Integer.parseInt(walletId)).get().getBalance();
    }


    //Adding money from bank to wallet
    @Override
    public void addMoney(Double money, String walletId, Integer accNo) {
        try {
            if(!bankAccountRepository.existsById(accNo))  //bank account exists means wallet exists
                throw new BankAccountNotFoundException("Bank account not found");
            BankAccount bankAccount = bankAccountRepository.findById(accNo).get();
            if(!bankAccount.getWallet().getWalletId().equals(Integer.parseInt(walletId)))
                throw new WalletIdMismatchException("Account number doesn't belong to walled Id");

            //Change in bank table


            if(bankAccount.getBalance()-money <= 1000)  //1000 is the minimum balance needed in an account
                throw new InsufficientFundsBankException("Insufficient funds in the bank account");
            bankAccount.setBalance(bankAccount.getBalance() - money);
            bankAccountRepository.save(bankAccount);

            //Change in wallet table

            Wallet wallet = walletRepository.findById(Integer.parseInt(walletId)).get();
            wallet.setBalance(wallet.getBalance() + money);
            walletRepository.save(wallet);


            //Add to transaction table

            Transaction t = new Transaction();
            Random random = new Random();
            t.setTransactionId(random.nextInt(100000));
            t.setTransactionType("Credit: Wallet");
            t.setTransactionDate(LocalDate.now());
            t.setAmount(money);
            t.setDescription("Debited from the bank account: "+ money);
            t.setWallet(wallet);
            t.setBankAccount(bankAccount);
            transactionRepository.save(t);
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }

    //Depositing money from wallet to bank
    @Override
    public void depositMoney(Double money, String walletId, Integer accNo) {

        try {
            if(!bankAccountRepository.existsById(accNo))  //bank account exists means wallet exists
                throw new BankAccountNotFoundException("Bank account not found");
            if(!bankAccountRepository.findById(accNo).get().getWallet().getWalletId().equals(Integer.parseInt(walletId)))
                throw new WalletIdMismatchException("Account number doesn't belong to walled Id");

            //Change in wallet table
            Wallet wallet = walletRepository.findById(Integer.parseInt(walletId)).get();
            if(wallet.getBalance()-money < 0)  //1000 is the minimum balance needed in an account
                throw new InsufficientFundsWalletException("Insufficient funds in the wallet account: Please recharge");
            wallet.setBalance(wallet.getBalance() - money);
            walletRepository.save(wallet);

            //Change in bank table
            BankAccount bankAccount = bankAccountRepository.findById(accNo).get();
            bankAccount.setBalance(bankAccount.getBalance() + money);
            bankAccountRepository.save(bankAccount);

            //Add to transaction table

            Transaction t = new Transaction();
            Random random = new Random();
            t.setTransactionId(random.nextInt(100000));
            t.setTransactionType("Debit: Wallet");
            t.setTransactionDate(LocalDate.now());
            t.setAmount(money);
            t.setDescription("Credited to the bank account "+money);
            t.setWallet(wallet);
            t.setBankAccount(bankAccount);
            transactionRepository.save(t);
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }

    @Override
    public void fundTransfer(Double money, Integer walletId,String benId, Integer accNo, Integer benAccNo){


        try {
            if(!bankAccountRepository.existsById(accNo))  //bank account exists means wallet exists
                throw new BankAccountNotFoundException("Bank account not found");
            if(!bankAccountRepository.findById(accNo).get().getWallet().getWalletId().equals(walletId))
                throw new WalletIdMismatchException("Account number doesn't belong to walled Id");

            if(!beneficiaryRepository.existsById(Integer.parseInt(benId))) //beneficiary account exists means beneficiary walletId also exists
                throw new BeneficiaryNotFoundException("Beneficiary account not found");

            BeneficieryDetails benf = beneficiaryRepository.findById(Integer.parseInt(benId)).get();

            if(!benf.getWallet().getWalletId().equals(walletId))
                throw new WalletIdMismatchException("beneficiary account doesn't correspond to wallet Id");

            //Deduct money to current user wallet
            Wallet userWallet= walletRepository.findById(walletId).get();
            if(userWallet.getBalance()-money < 0)  //1000 is the minimum balance needed in an account
                throw new InsufficientFundsWalletException("Insufficient funds in the wallet account for fund transfer: Please Recharge");
            userWallet.setBalance(userWallet.getBalance()-money);
            walletRepository.save(userWallet);

            //Add money to beneficiary wallet
            Wallet beneficiaryWallet= walletRepository.findById(benf.getBeneficiaryWalletId()).get();
            beneficiaryWallet.setBalance(beneficiaryWallet.getBalance()+money);
            walletRepository.save(beneficiaryWallet);


            //Update transaction Table: Sender Update
            Transaction t= new Transaction();
            Random random = new Random();
            int transactionId= random.nextInt(100000);
            String temp= transactionId+"0";   //0 for Debit
            t.setTransactionId(Integer.parseInt(temp));
            t.setTransactionType("Debit: Wallet");
            t.setTransactionDate(LocalDate.now());
            t.setAmount(money);
            t.setDescription("Sent to beneficiary's wallet and Bank account");
            t.setWallet(userWallet);
            t.setBankAccount(bankAccountRepository.findById(accNo).get());
            transactionRepository.save(t);

            //Update transaction Table: Receiver Update
            temp= transactionId+"1"; // 1 for Credit
            t.setTransactionId(Integer.parseInt(temp));
            t.setTransactionType("Credit: Wallet ");
            t.setTransactionDate(LocalDate.now());
            t.setAmount(money);
            t.setDescription("Transaction received from sender's wallet id:"+userWallet.getWalletId());
            t.setWallet(beneficiaryWallet);
            t.setBankAccount(bankAccountRepository.findById(benAccNo).get());
            transactionRepository.save(t);

        }catch(Exception E) {
            E.printStackTrace();
        }

    }

    @Override
    public void changePassword(Integer walletId, String password) {

        Wallet wallet= walletRepository.findById(walletId).get();
        Customer customer= customerRepository.findByWallet(wallet);
        customer.setPassword(password);
        customerRepository.save(customer);

    }

    @Override
    public String logout() {
        jwtRepository.deleteAll();
        return "Session Terminated";
    }


}
