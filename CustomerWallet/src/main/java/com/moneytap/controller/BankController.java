package com.moneytap.controller;

import com.moneytap.model.BankAccount;
import com.moneytap.model.BankAccountList;
import com.moneytap.repository.WalletRepository;
import com.moneytap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@RestController
@RequestMapping("/Bank")
public class BankController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/viewAccounts")
    public ArrayList<BankAccount> getAccounts(){
        BankAccountList bList= restTemplate.getForObject("http://bankAccount-service/bank/viewAccounts/"+UserService.WALLET_ID
                ,BankAccountList.class);
        assert bList != null;
        return bList.getBaList();
    }

    @PostMapping("/addAccount")
    public void addAccount(@RequestBody BankAccount bankAccount){
        restTemplate.postForObject("http://bankAccount-service/bank/addAccount/"+UserService.WALLET_ID,
                bankAccount,BankAccount.class);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/removeAccount")
    public void deleteAccount(@RequestParam Integer accNo){

        String Url = "http://bankAccount-service/bank/removeAccount/"+UserService.WALLET_ID;

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(Url)
                .queryParam("accNo", accNo);
        restTemplate.delete(builder.toUriString());
    }
}
