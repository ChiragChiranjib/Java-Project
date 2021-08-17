package com.moneytap.OnlinePaymentDiscoveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OnlinePaymentDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinePaymentDiscoveryServerApplication.class, args);
	}

}
