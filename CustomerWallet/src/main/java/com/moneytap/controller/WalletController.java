package com.moneytap.controller;

import com.moneytap.model.Customer;
import com.moneytap.service.UserService;
import com.moneytap.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WalletService walletService;



    //To be used by the admin
    @GetMapping("/getCustomers")
    public ArrayList<Customer> getCustomers(){

           if( !UserService.checkAdmin())
        return walletService.getCustomers();
           else
               return null;
    }

    @GetMapping("/getBalance")
    public double getBalance(){
        return walletService.getBalance(UserService.WALLET_ID);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestParam String password){
       walletService.changePassword(Integer.parseInt(UserService.WALLET_ID),password);
    }

    @PostMapping("/addMoney")
    public void addMoney(@RequestParam Double money, @RequestParam Integer accNo){
        walletService.addMoney(money,UserService.WALLET_ID,accNo);
    }

    @PostMapping("/depositMoney")
    public void depositMoney(@RequestParam Double money, @RequestParam Integer accNo){
        walletService.depositMoney(money,UserService.WALLET_ID,accNo);
    }

    @PostMapping("/fundTransfer/{benId}")
    public void fundTransfer(@RequestParam Double money,@RequestParam Integer accNo, @RequestParam Integer benAccNo,
                             @PathVariable String benId){
        walletService.fundTransfer(money,Integer.parseInt(UserService.WALLET_ID),benId,accNo,benAccNo);
    }

    @GetMapping("/sessionTerminated")
    public String sessionLogout(){
        return walletService.logout();
    }

}
