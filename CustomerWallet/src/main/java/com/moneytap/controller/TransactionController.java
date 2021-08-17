package com.moneytap.controller;

import com.moneytap.model.Transaction;
import com.moneytap.model.TransactionList;
import com.moneytap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/allTransactions")
    public ArrayList<Transaction> getTransactions(){
        TransactionList t= restTemplate.getForObject("http://transaction-service/transaction/allTransactions/"+UserService.WALLET_ID
                ,TransactionList.class);
        assert t != null;
        return t.gettList();
    }

    @GetMapping("/transactionsByDate/{date}")
    public ArrayList<Transaction> getTransactionsByDate(@PathVariable String date){
        TransactionList t= restTemplate.getForObject("http://transaction-service/transaction/transactionsByDate/"+UserService.WALLET_ID+"/"+date
                ,TransactionList.class);
        assert t != null;
        return t.gettList();
    }

    @GetMapping("/transactionsByType/{type}")
    public ArrayList<Transaction> getTransactionsByType(@PathVariable String type){
        TransactionList t= restTemplate.getForObject("http://transaction-service/transaction/transactionsByType/"+UserService.WALLET_ID+"/"+type
                ,TransactionList.class);
        assert t != null;
        return t.gettList();
    }

    @GetMapping("/transactionsByAccNo/{AccNo}")
    public ArrayList<Transaction> getTransactionsByAccNo(@PathVariable String AccNo){
        TransactionList t= restTemplate.getForObject("http://transaction-service/transaction/transactionsByAccNo/"+UserService.WALLET_ID+"/"+AccNo
                ,TransactionList.class);
        assert t != null;
        return t.gettList();
    }
}
