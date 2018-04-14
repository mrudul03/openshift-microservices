package com.microservices.composite.gateway;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservices.composite.contract.PaymentAccountResource;

@FeignClient(name="payment-account-service", url="http://payment-account-service:8080")
public interface PaymentAccountClient {
	
	//http://payment-account-service:8080
	
	@RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/accounts/")
	List<PaymentAccountResource> getAllAccounts(@PathVariable("customerId") Long customerId);

}
