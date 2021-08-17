package com.moneytap.controller;

import com.moneytap.model.BeneficieryDetails;
import com.moneytap.model.BeneficieryDetailsList;
import com.moneytap.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    @Autowired
    BeneficiaryService beneficiaryService;

    @GetMapping("/getAlLDetails/{walletId}")
    public BeneficieryDetailsList getAllDetails(@PathVariable String walletId){

        return beneficiaryService.getAllDetails(Integer.parseInt(walletId));
    }

    @PostMapping("/addDetails/{walletId}")
    public void addAccount(@RequestBody BeneficieryDetails beneficieryDetails,
                           @PathVariable String walletId){
        beneficiaryService.addAccount(Integer.parseInt(walletId),beneficieryDetails);
    }

    @DeleteMapping("/removeDetails/{walletId}/{benId}")
    public void deleteAccount(@PathVariable String benId,@PathVariable String walletId){
        beneficiaryService.deleteAccount(Integer.parseInt(walletId),benId);
    }

    @GetMapping("/getDetail/{walletId}/{benId}")
    public BeneficieryDetails getDetail(@PathVariable String benId, @PathVariable String walletId){
        return beneficiaryService.viewAccount(Integer.parseInt(walletId),benId);
    }
}
