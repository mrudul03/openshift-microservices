package com.microservices.composite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.composite.contract.CustomerAccountResource;
import com.microservices.composite.contract.CustomerResource;
import com.microservices.composite.contract.PaymentAccountResource;
import com.microservices.composite.gateway.CustomerClient;
import com.microservices.composite.gateway.FeignClientBuilder;
import com.microservices.composite.gateway.PaymentAccountClient;
import com.microservices.composite.transform.CustomerCompositeTransformer;

@Service
public class CustomerAccountService {
	
	private FeignClientBuilder feignClientBuilder = new FeignClientBuilder();
	
	@Autowired
	private CustomerCompositeTransformer compositeTransformer;

	public CustomerAccountResource getCustomerAccountDetails(Long customerId){
		PaymentAccountClient paymentAccountClient = feignClientBuilder.getAccountClient();
		CustomerClient customerClient = feignClientBuilder.getCustomerClient();
		
		CustomerResource customerResource = customerClient.getCustomerDetails(customerId);
		List<PaymentAccountResource> paymentAccounts = paymentAccountClient.getAllAccounts(customerId);
		return compositeTransformer.transformCustomerAccount(paymentAccounts, customerResource);
	}
}
