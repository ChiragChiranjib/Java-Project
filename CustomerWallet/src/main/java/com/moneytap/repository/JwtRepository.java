package com.moneytap.repository;

import com.moneytap.model.CustomerResponseToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRepository extends CrudRepository<CustomerResponseToken, String> {
}
