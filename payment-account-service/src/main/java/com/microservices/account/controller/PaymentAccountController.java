package com.microservices.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.account.contract.PaymentAccountResource;
import com.microservices.account.domain.PaymentAccountService;
import com.microservices.account.repository.PaymentAccountEntity;
import com.microservices.account.transform.EntityToResourceTransformer;

@RestController
public class PaymentAccountController {
	
	@Autowired
	private PaymentAccountService paymentAccountService;
	
	@Autowired
	private EntityToResourceTransformer entityToResourceTransformer;
	
	@RequestMapping(value="/customers/{customerId}/accounts/{accountId}", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<PaymentAccountResource> getCustomer(@PathVariable Long customerId,
			@PathVariable Long accountId){
		
		System.out.println("Got a request for payment account.....................");
		return ResponseEntity.ok(entityToResourceTransformer.transformAccount(
				paymentAccountService.getPaymentAccount(accountId)));
	}
	
	@RequestMapping(value="/customers/{customerId}/accounts/", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<PaymentAccountResource>> getAllAccounts(@PathVariable Long customerId){
		
		System.out.println("Got a request for all payment accounts.....................");
		return ResponseEntity.ok(entityToResourceTransformer.transformAccountList(
				paymentAccountService.getPaymentAccounts(customerId)));
	}
	
	@RequestMapping(value="/customers/{customerId}/accounts/", 
			method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<PaymentAccountResource> createAccount(
			@PathVariable Long customerId,
			@RequestBody PaymentAccountResource accountResource){
		
		System.out.println("In createAccount Controller ##############");
		
		accountResource.setCustomerId(customerId);
		PaymentAccountEntity accountEntity = paymentAccountService.createPaymentAccount(
				accountResource);
		return ResponseEntity.ok(entityToResourceTransformer.transformAccount(accountEntity));
	}

}
