package com.microservices.composite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.composite.contract.CustomerAccountResource;
import com.microservices.composite.service.CustomerAccountService;

@RestController
public class CustomerAccountController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CustomerAccountService customerAccountService;
	
	@RequestMapping(value="/customers/{customerId}", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CustomerAccountResource> getCustomerAccountDetails(
			@PathVariable Long customerId){
		
		System.out.println("In CustomerAccountController");
		logger.info("In CustomerAccountController............");
		return ResponseEntity.ok(customerAccountService.getCustomerAccountDetails(customerId));
	}

}
