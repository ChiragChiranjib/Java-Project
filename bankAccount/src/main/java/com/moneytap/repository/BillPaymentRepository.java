package com.moneytap.repository;

import com.moneytap.model.BankAccount;
import com.moneytap.model.BillPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentRepository extends CrudRepository<BillPayment,String> {
    public void deleteAllByBankAccount(BankAccount bankAccount);
}
