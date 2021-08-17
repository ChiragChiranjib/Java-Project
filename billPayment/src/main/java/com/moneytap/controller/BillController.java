package com.moneytap.controller;


import com.moneytap.model.BillPayment;
import com.moneytap.model.BillPaymentList;
import com.moneytap.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billPayment")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("/viewBills/{walletId}")
    public BillPaymentList getBills(@PathVariable String walletId){
        return billService.getBills(Integer.parseInt(walletId));
    }

    @PostMapping("/addBill/{walletId}/{accNo}")
    public void addBills(@PathVariable String walletId
            , @RequestBody BillPayment bill,@PathVariable String accNo){
        billService.addBills(Integer.parseInt(walletId),bill,Integer.parseInt(accNo));
    }
}
