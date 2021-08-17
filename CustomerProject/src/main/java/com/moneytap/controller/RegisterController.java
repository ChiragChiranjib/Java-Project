package com.moneytap.controller;


import com.moneytap.model.Customer;


import com.moneytap.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/userRegistration")
    public void addCustomer(@RequestBody Customer customer){
        registerService.addCustomer(customer);
    }

}
