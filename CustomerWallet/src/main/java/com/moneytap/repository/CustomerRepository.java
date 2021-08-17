package com.moneytap.repository;

import com.moneytap.model.Customer;
import com.moneytap.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    public Customer findByWallet(Wallet wallet);
    public Customer findByName(String name);
}
