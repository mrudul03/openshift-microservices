package com.microservices.composite.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservices.composite.contract.CustomerResource;

@FeignClient(name="customer-service", url="http://customer-service:8080")
public interface CustomerClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}")
	CustomerResource getCustomer(@PathVariable("customerId") Long customerId);


}
