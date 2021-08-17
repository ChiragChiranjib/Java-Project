package com.moneytap.controller;

import com.moneytap.model.BeneficieryDetails;
import com.moneytap.model.BeneficieryDetailsList;
import com.moneytap.repository.WalletRepository;
import com.moneytap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getAlLDetails")
    public ArrayList<BeneficieryDetails> getAllDetails(){

        BeneficieryDetailsList bList= restTemplate.getForObject("http://beneficiaryDetails-service/beneficiary/getAlLDetails/"+UserService.WALLET_ID
                ,BeneficieryDetailsList.class);
        assert bList != null;
        return bList.getBdList();
    }

    @PostMapping("/addDetails")
    public void addAccount(@RequestBody BeneficieryDetails beneficieryDetails){
        restTemplate.postForObject("http://beneficiaryDetails-service/beneficiary/addDetails/"+UserService.WALLET_ID,
                beneficieryDetails,BeneficieryDetails.class);
    }

    @DeleteMapping("/removeDetails/{benId}")
    public void deleteAccount(@PathVariable String benId){
       restTemplate.delete("http://beneficiaryDetails-service/beneficiary/removeDetails/"+UserService.WALLET_ID+"/"+benId);
    }

    @GetMapping("/viewDetail")
    public BeneficieryDetails getDetail(@RequestParam String benId){
        return restTemplate.getForObject("http://beneficiaryDetails-service/beneficiary/getDetail/"+UserService.WALLET_ID+"/"+benId
                ,BeneficieryDetails.class);

    }
}
