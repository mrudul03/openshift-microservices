package com.microservices.composite.gateway;
import java.util.List;

import com.microservices.composite.contract.PaymentAccountResource;

import feign.Param;
import feign.RequestLine;

public interface PaymentAccountClient {
	
	@RequestLine("GET /customers/{customerId}/accounts/")
	List<PaymentAccountResource> getAllAccounts(@Param("customerId") Long customerId);

}
