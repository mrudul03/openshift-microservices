package com.microservices.customers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.customers.contract.CustomerResource;
import com.microservices.customers.domain.CustomerService;
import com.microservices.customers.repository.CustomerEntity;
import com.microservices.customers.transform.EntityToResourceTransformer;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EntityToResourceTransformer resourceTransformer;
	
	@RequestMapping(value="/customers/", 
			method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<CustomerResource> createCustomer(@RequestBody CustomerResource customerResource ){
		CustomerEntity customerEntity = customerService.createCustomer(customerResource);
		return ResponseEntity.ok(resourceTransformer.transformCustomer(customerEntity));
	}
	
	@RequestMapping(value="/customers/{customerId}", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CustomerResource> getCustomer(@PathVariable Long customerId ){
		CustomerEntity customerEntity = customerService.getCustomer(customerId);
		return ResponseEntity.ok(resourceTransformer.transformCustomer(customerEntity));
	}
	
	@RequestMapping(value="/customers/{customerId}", 
			method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId ){
		customerService.deleteCustomer(customerId);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/customers/", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<CustomerResource>> getAllCustomers(){
		List<CustomerEntity> customers = customerService.getAllCustomers();
		List<CustomerResource> customerResources = resourceTransformer.transformCustomerList(customers);
		return ResponseEntity.ok(customerResources);
	}
}
