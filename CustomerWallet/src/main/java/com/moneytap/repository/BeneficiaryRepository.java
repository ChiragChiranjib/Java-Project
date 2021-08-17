package com.moneytap.repository;

import com.moneytap.model.BeneficieryDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends CrudRepository<BeneficieryDetails,Integer> {
}
