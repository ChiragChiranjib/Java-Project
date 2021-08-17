package com.moneytap.service;

import com.moneytap.exception.AccessDeniedException;
import com.moneytap.exception.InvalidWalletException;
import com.moneytap.model.Customer;
import com.moneytap.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    public static String WALLET_ID;
    public static String username;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Database
        Customer customer= customerRepository.findByName(userName);
        username= customer.getName();
        String password= customer.getPassword();
        WALLET_ID = customer.getWallet().getWalletId().toString();
        return new User(username,password,new ArrayList<>());
    }

    public static void checkWallet(String walletId){
        if(UserService.WALLET_ID.equals(walletId)) {
            try {
                throw new InvalidWalletException("WalletId Entered is incorrect");
            }catch (Exception E){E.printStackTrace();}
        }
    }

    public static boolean checkAdmin(){
        try {
            if (!username.equals("Admin")) {
                throw new AccessDeniedException("ACCESS DENIED!");
            }
        }catch(Exception E){
            E.printStackTrace();
            return true;
        }
        return false;
    }
}
