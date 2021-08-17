package com.moneytap.repository;

import com.moneytap.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    public boolean existsByName(String name);
}
