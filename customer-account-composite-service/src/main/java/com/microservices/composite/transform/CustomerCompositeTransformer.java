package com.microservices.composite.transform;

import java.util.List;

import org.springframework.stereotype.Component;

import com.microservices.composite.contract.CustomerAccountResource;
import com.microservices.composite.contract.CustomerResource;
import com.microservices.composite.contract.PaymentAccountResource;

@Component
public class CustomerCompositeTransformer {
	
	public CustomerAccountResource transformCustomerAccount(
			List<PaymentAccountResource> accountResources, CustomerResource customerResource){
		
		CustomerAccountResource resource = new CustomerAccountResource();
		resource.setCustomerId(customerResource.getCustomerId());
		resource.setFirstName(customerResource.getFirstName());
		resource.setLastName(customerResource.getLastName());
		resource.setAccounts(accountResources);
		
		return resource;
	}

}
