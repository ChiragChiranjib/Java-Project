package com.moneytap.service;

import com.moneytap.exception.*;
import com.moneytap.model.BeneficieryDetails;
import com.moneytap.model.BeneficieryDetailsList;
import com.moneytap.model.Wallet;
import com.moneytap.repository.BeneficiaryRepository;
import com.moneytap.repository.CustomerRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{

    @Autowired
    BeneficiaryRepository beneficiaryRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public BeneficieryDetailsList getAllDetails(Integer walletId) {

        BeneficieryDetailsList bList= new BeneficieryDetailsList();

                Wallet wallet = walletRepository.findById(walletId).get();
                List<BeneficieryDetails> list = new ArrayList<>(beneficiaryRepository.findAllByWallet(wallet));
                bList.setBdList((ArrayList<BeneficieryDetails>) list);

        return bList;
    }

    @Override
    public void addAccount(int walletId, BeneficieryDetails beneficieryDetails) {
        beneficieryDetails.setWallet(walletRepository.findById(walletId).get());
        try {
            if(!walletRepository.existsById(beneficieryDetails.getBeneficiaryWalletId())  || !customerRepository.existsByName(beneficieryDetails.getBeneficiaryName()))
                throw new InvalidWalletException("Invalid Wallet: Beneficiary wallet not registered with Wallet");
            if(beneficieryDetails.getBeneficiaryWalletId()==walletId)
                throw new SelfBeneficiaryException("Cannot make itself a beneficiary");

            if (beneficiaryRepository.findAllByWallet(walletRepository.findById(walletId).get()).stream()
                    .anyMatch( beneficiary -> beneficiary.getBeneficiaryWalletId()== beneficieryDetails.getBeneficiaryWalletId()))
                throw new DuplicateBeneficiaryException("Beneficiary details already exists");

                beneficiaryRepository.save(beneficieryDetails);

        }
        catch (Exception E){
            E.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(int walletId, String benId) {
        try {
            if (beneficiaryRepository.existsById(Integer.parseInt(benId))) {   // if exists both userWalletId (Table constraint) and beneficiaryId exists (Self imposed constraint)

                if(!beneficiaryRepository.findById(Integer.parseInt(benId)).get().getWallet().getWalletId().equals(walletId))
                    throw new WalletIdMismatchException("No beneficiary account corresponding to wallet ID");
                beneficiaryRepository.deleteById(Integer.parseInt(benId));
            }
            else{
               throw new BeneficiaryAccountNotFoundException("Beneficiary account to be deleted not found");
            }
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }

    @Override
    public BeneficieryDetails viewAccount(int walletId, String benId) {
        try {
            if (beneficiaryRepository.existsById(Integer.parseInt(benId))) { // if exists both userWalletId (Table constraint) and beneficiaryId exists (Self imposed constraint)

                if(!beneficiaryRepository.findById(Integer.parseInt(benId)).get().getWallet().getWalletId().equals(walletId))
                    throw new WalletIdMismatchException("No beneficiary account corresponding to wallet ID");

                Wallet wallet= walletRepository.findById(walletId).get();
                List<BeneficieryDetails> list= new ArrayList<>(beneficiaryRepository.findAllByWallet(wallet));

                return list.stream().filter( B-> B.getBeneficiaryId().equals(Integer.parseInt(benId))).findFirst().get();
            }
            else{
                throw new BeneficiaryAccountNotFoundException("Beneficiary account to be deleted not found");
            }
        }
        catch(Exception E){
            E.printStackTrace();
        }

        return null;
    }


}
