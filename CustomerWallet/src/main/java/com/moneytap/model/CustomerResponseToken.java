package com.moneytap.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerResponseToken {

    @Id
    private String jwtToken;

    public CustomerResponseToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public CustomerResponseToken() {
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
