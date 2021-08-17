package com.moneytap.controller;

import com.moneytap.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/userRegistration")
public class RegisterController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public void userRegistration(@RequestBody Customer customer){
        restTemplate.postForObject("http://customerRegistration-service/customer/userRegistration",customer,Customer.class);
    }

}
