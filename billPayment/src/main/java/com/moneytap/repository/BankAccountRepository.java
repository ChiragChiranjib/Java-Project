package com.moneytap.repository;

import com.moneytap.model.BankAccount;
import com.moneytap.model.Wallet;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {

    public List<BankAccount> findAllByWallet(Wallet wallet);
}
