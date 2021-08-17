package com.moneytap.controller;

import com.moneytap.model.Transaction;
import com.moneytap.model.TransactionList;
import com.moneytap.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/allTransactions/{walletId}")
    public TransactionList getTransactions(@PathVariable String walletId){
        return transactionService.getTransactions(Integer.parseInt(walletId));
    }

    @GetMapping("/transactionsByDate/{walletId}/{date}")
    public TransactionList getTransactionsByDate(@PathVariable String walletId,
                                             @PathVariable String date){
        return transactionService.getTransactionsByDate(Integer.parseInt(walletId),LocalDate.parse(date));
    }

    @GetMapping("/transactionsByType/{walletId}/{type}")
    public TransactionList getTransactionsByType(@PathVariable String walletId,
                                                 @PathVariable String type){

        return transactionService.getTransactionsByType(Integer.parseInt(walletId),type);
    }

    @GetMapping("/transactionsByAccNo/{walletId}/{accNo}")
    public TransactionList getTransactionsByAccNo(@PathVariable String walletId, @PathVariable String accNo){
        return transactionService.getTransactionsByAccNo(Integer.parseInt(walletId),Integer.parseInt(accNo));
    }

}
