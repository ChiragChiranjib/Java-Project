package com.moneytap.repository;

import com.moneytap.model.BankAccount;
import com.moneytap.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {

}
