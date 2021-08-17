package com.moneytap.model;

import java.util.ArrayList;

public class TransactionList {

    private ArrayList<Transaction> tList;

    public ArrayList<Transaction> gettList() {
        return tList;
    }

    public void settList(ArrayList<Transaction> tList) {
        this.tList = tList;
    }
}
