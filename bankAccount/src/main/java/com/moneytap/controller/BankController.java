package com.moneytap.controller;

import com.moneytap.model.BankAccount;
import com.moneytap.model.BankAccountList;
import com.moneytap.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("/viewAccounts/{walletId}")
    public BankAccountList getAccounts(@PathVariable String walletId){
        return bankService.getAccounts(Integer.parseInt(walletId));
    }

    @PostMapping("/addAccount/{walletId}")
    public void addAccount(@RequestBody BankAccount bankAccount,
                           @PathVariable String walletId){
        bankService.addAccount(Integer.parseInt(walletId),bankAccount);
    }

    @RequestMapping(method= RequestMethod.DELETE,value="/removeAccount/{walletId}")
    public void deleteAccount(@RequestParam Integer accNo,
                              @PathVariable String walletId){
        bankService.deleteAccount(Integer.parseInt(walletId),accNo);
    }
}
