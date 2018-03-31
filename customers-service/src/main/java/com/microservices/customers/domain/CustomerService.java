package com.microservices.customers.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customers.contract.CustomerResource;
import com.microservices.customers.repository.CustomerEntity;
import com.microservices.customers.repository.CustomersRepository;
import com.microservices.customers.transform.ResourceToEntityTransformer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomersRepository customersRepository;
	
	@Autowired
	private ResourceToEntityTransformer entityTransformer;
	
	public CustomerEntity createCustomer(CustomerResource customerResource){
		CustomerEntity customerEntity = entityTransformer.transformCustomer(customerResource);
		return customersRepository.save(customerEntity);
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
