package com.moneytap.repository;

import com.moneytap.model.BankAccount;
import com.moneytap.model.Transaction;
import com.moneytap.model.Wallet;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

        public List<Transaction> findAllByWallet(Wallet wallet);
        public List<Transaction> findAllByBankAccount(BankAccount bankAccount);
}
