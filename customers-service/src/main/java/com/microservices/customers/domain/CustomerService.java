package com.microservices.customers.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customers.contract.CustomerResource;
import com.microservices.customers.infrastructure.messaging.CreatePaymentAccountCommand;
import com.microservices.customers.infrastructure.messaging.CustomerEventPublisher;
import com.microservices.customers.repository.CustomerEntity;
import com.microservices.customers.repository.CustomersRepository;
import com.microservices.customers.transform.ResourceToEntityTransformer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomersRepository customersRepository;
	
	@Autowired
	private ResourceToEntityTransformer entityTransformer;
	
	@Autowired
	private CustomerEventPublisher accountEventPublisher;
	
	public CustomerEntity createCustomer(CustomerResource customerResource){
		System.out.println("In createCustomer Service");
		CustomerEntity customerEntity = entityTransformer.transformCustomer(customerResource);
		CustomerEntity savedCustomer = customersRepository.save(customerEntity);
		if(null != accountEventPublisher){
			System.out.println("In service now - Publishing message ####################:");
			CreatePaymentAccountCommand createPaymentAccountCommand = new CreatePaymentAccountCommand(
					savedCustomer.getId(), savedCustomer.getFirstName(), savedCustomer.getLastName());
			accountEventPublisher.sendCustomerData(createPaymentAccountCommand);
		}
		
		return savedCustomer;
	}
	
	public CustomerEntity getCustomer(Long customerId){
		return customersRepository.findOne(customerId);
	}
	
	public void deleteCustomer(Long customerId){
		CustomerEntity customerEntity = customersRepository.findOne(customerId);
		customersRepository.delete(customerEntity);
	}
	
	public List<CustomerEntity> getAllCustomers(){
		return customersRepository.findAll();
	}
	
	
}
