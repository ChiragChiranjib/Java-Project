package com.moneytap.repository;

import com.moneytap.model.BankAccount;
import com.moneytap.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {

    public BankAccount findAllByWallet(Wallet wallet);
}
