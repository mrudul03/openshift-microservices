package com.microservices.customers.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.customers.contract.AddressResource;
import com.microservices.customers.contract.ContactResource;
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
	public ResponseEntity<CustomerResource> createCustomer(
			@RequestBody CustomerResource customerResource ){
		System.out.println("In createCustomer Controller");
		
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
		
		System.out.println("in getAllCustomers.........");
		
//		List<CustomerEntity> customers = customerService.getAllCustomers();
//		List<CustomerResource> customerResources = resourceTransformer.transformCustomerList(customers);
//		return ResponseEntity.ok(customerResources);
		
		return ResponseEntity.ok(createDummyCustomers());
	}
	
	private List<CustomerResource> createDummyCustomers(){
		ContactResource contactResource = new ContactResource();
		contactResource.setContactNumber("1212121212");
		contactResource.setContactType("mobile");
		
		AddressResource addressResource = new AddressResource();
		addressResource.setAddressType("Home Address");
		addressResource.setAreaCode("3000");
		addressResource.setCity("City");
		addressResource.setState("State");
		addressResource.setStreetAddress("Street Address");
		
		CustomerResource customerResource = new CustomerResource();
		customerResource.setCustomerId(Long.valueOf(1));
		customerResource.setBirthDate(new Date());
		customerResource.setFirstName("First Name");
		customerResource.setLastName("Last Name");
		customerResource.setContacts(Collections.singletonList(contactResource));
		customerResource.setAddresses(Collections.singletonList(addressResource));
		return Collections.singletonList(customerResource);
	}
}
