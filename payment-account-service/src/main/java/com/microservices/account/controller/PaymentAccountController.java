package com.microservices.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.account.contract.PaymentAccountResource;
import com.microservices.account.domain.PaymentAccountService;

@RestController
public class PaymentAccountController {
	
	@Autowired
	private PaymentAccountService paymentAccountService;
	
	@RequestMapping(value="/customers/{customerId}/accounts/{accountId}", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<PaymentAccountResource> getCustomer(@PathVariable Long customerId,
			@PathVariable Long accountId){
		
		System.out.println("Got a request for payment account.....................");
		
		PaymentAccountResource accountResource = new PaymentAccountResource("MyAccount", "Acc123", 123.00);
		return ResponseEntity.ok(accountResource);
	}

}
