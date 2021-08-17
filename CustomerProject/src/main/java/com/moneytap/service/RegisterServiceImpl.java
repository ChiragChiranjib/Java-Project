package com.moneytap.service;


import com.moneytap.model.Customer;
import com.moneytap.repository.CustomerRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletRepository walletRepository;



    @Override
    public void addCustomer(Customer customer) {
                customerRepository.save(customer);
    }


}
