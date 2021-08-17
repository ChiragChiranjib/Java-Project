package com.moneytap.service;

import com.moneytap.model.BeneficieryDetails;
import com.moneytap.model.BeneficieryDetailsList;

public interface BeneficiaryService {
    BeneficieryDetailsList getAllDetails(Integer walletId);

    void addAccount(int walletId, BeneficieryDetails beneficieryDetails);

    void deleteAccount(int walletId, String benId);

    BeneficieryDetails viewAccount(int walletId, String benId);
}
