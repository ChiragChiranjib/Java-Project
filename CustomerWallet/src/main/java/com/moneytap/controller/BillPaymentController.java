package com.moneytap.controller;

import com.moneytap.model.BillPayment;
import com.moneytap.model.BillPaymentList;
import com.moneytap.repository.BankAccountRepository;
import com.moneytap.repository.WalletRepository;
import com.moneytap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/billPayment")
public class BillPaymentController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/viewBills")
    public ArrayList<BillPayment> getBills(){

        BillPaymentList bList= restTemplate.getForObject("http://billPayment-service/billPayment/viewBills/"+UserService.WALLET_ID
                ,BillPaymentList.class);
        assert bList != null;
        return bList.getBpList();
    }

    @PostMapping("/addBill/{accNo}")
    public void addBills(@RequestBody BillPayment bill, @PathVariable int accNo){

        restTemplate.postForObject("http://billPayment-service/billPayment/addBill/"+UserService.WALLET_ID+"/"+accNo,
                bill,BillPayment.class);
    }

}
