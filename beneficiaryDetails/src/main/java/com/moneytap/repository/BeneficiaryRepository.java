package com.moneytap.repository;

import com.moneytap.model.BeneficieryDetails;
import com.moneytap.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BeneficiaryRepository extends CrudRepository<BeneficieryDetails,Integer> {

    public List<BeneficieryDetails> findAllByWallet(Wallet wallet);
}
