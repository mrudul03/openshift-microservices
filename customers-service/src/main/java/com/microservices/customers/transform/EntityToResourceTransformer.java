package com.microservices.customers.transform;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.microservices.customers.contract.AddressResource;
import com.microservices.customers.contract.ContactResource;
import com.microservices.customers.contract.CustomerResource;
import com.microservices.customers.repository.AddressEntity;
import com.microservices.customers.repository.ContactEntity;
import com.microservices.customers.repository.CustomerEntity;

@Component
public class EntityToResourceTransformer {
	
	private Function<CustomerEntity, CustomerResource> transformCustomerToResource = new 
			Function<CustomerEntity, CustomerResource>() {
		
	    public CustomerResource apply(CustomerEntity customerEntity) {
	    	CustomerResource customerResource = new CustomerResource();
	    	customerResource.setFirstName(customerEntity.getFirstName());
	    	customerResource.setLastName(customerEntity.getLastName());
	    	customerResource.setBirthDate(customerEntity.getBirthDate());
	        return customerResource;
	    }
	};
	
	Function<AddressEntity, AddressResource> transformAddressToResource = new 
			Function<AddressEntity, AddressResource>() {

	    public AddressResource apply(AddressEntity addressEntity) {
	    	AddressResource addressResource = new AddressResource();
	    	addressResource.setAddressType(addressEntity.getAddressType());
	    	addressResource.setAreaCode(addressEntity.getAreaCode());
	    	addressResource.setCity(addressEntity.getCity());
	    	addressResource.setState(addressEntity.getState());
	    	addressResource.setStreetAddress(addressEntity.getStreetAddress());
	        return addressResource;
	    }
	};
	
	Function<ContactEntity, ContactResource> transformContactToResource = new 
			Function<ContactEntity, ContactResource>() {

	    public ContactResource apply(ContactEntity contactEntity) {
	    	ContactResource contactResource = new ContactResource();
	    	contactResource.setContactNumber(contactEntity.getContactNumber());
	    	contactResource.setContactType(contactEntity.getContactType());
			
	        return contactResource;
	    }
	};
	
	public CustomerResource transformCustomer(CustomerEntity customerEntity){
		
		List<AddressResource> addressResource = customerEntity.getAddress().stream().
				map(transformAddressToResource).collect(Collectors.toList());
		
		List<ContactResource> contactResource = customerEntity.getContacts().stream().
				map(transformContactToResource).collect(Collectors.toList());
		
		CustomerResource customerResource = transformCustomerToResource.apply(customerEntity);
		customerResource.setAddresses(addressResource);
		customerResource.setContacts(contactResource);
		
		return customerResource;
	}
	
	public List<CustomerResource> transformCustomerList(List<CustomerEntity> customerEntities){
		List<CustomerResource> customerResources = customerEntities.stream().
				map(entity -> transformCustomer(entity)).
				collect(Collectors.toList());
		return customerResources;
	}


}
