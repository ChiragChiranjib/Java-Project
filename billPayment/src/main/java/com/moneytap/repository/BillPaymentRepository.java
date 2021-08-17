package com.moneytap.repository;


import com.moneytap.model.BillPayment;
import com.moneytap.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillPaymentRepository extends CrudRepository<BillPayment,String> {
    public List<BillPayment> findAllByWallet(Wallet wallet);
}
