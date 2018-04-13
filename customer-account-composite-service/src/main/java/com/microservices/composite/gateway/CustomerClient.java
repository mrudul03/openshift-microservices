package com.microservices.composite.gateway;

import com.microservices.composite.contract.CustomerResource;

import feign.Param;
import feign.RequestLine;

public interface CustomerClient {
	
	@RequestLine("GET /customers/{customerId}")
	CustomerResource getCustomerDetails(@Param("customerId") Long customerId);


}
