package com.moneytap.service;

import com.moneytap.model.BillPayment;
import com.moneytap.model.BillPaymentList;

public interface BillService {
    BillPaymentList getBills(int walletId);

    void addBills(int parseInt, BillPayment bill, int accNo);
}
