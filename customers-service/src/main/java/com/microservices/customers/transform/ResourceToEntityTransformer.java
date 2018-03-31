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
public class ResourceToEntityTransformer {
	
	private Function<CustomerResource, CustomerEntity> transformCustomerToEntity = new 
			Function<CustomerResource, CustomerEntity>() {
		
	    public CustomerEntity apply(CustomerResource customerResource) {
	    	CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setFirstName(customerResource.getFirstName());
			customerEntity.setLastName(customerResource.getLastName());
			customerEntity.setBirthDate(customerResource.getBirthDate());
	        return customerEntity;
	    }
	};
	
	Function<AddressResource, AddressEntity> transformAddressToEntity = new 
			Function<AddressResource, AddressEntity>() {

	    public AddressEntity apply(AddressResource addressResource) {
	    	AddressEntity addressEntity = new AddressEntity();
			addressEntity.setAddressType(addressResource.getAddressType());
			addressEntity.setAreaCode(addressResource.getAreaCode());
			addressEntity.setCity(addressResource.getCity());
			addressEntity.setState(addressResource.getState());
			addressEntity.setStreetAddress(addressResource.getStreetAddress());
	        return addressEntity;
	    }
	};
	
	Function<ContactResource, ContactEntity> transformContactToEntity = new 
			Function<ContactResource, ContactEntity>() {

	    public ContactEntity apply(ContactResource contactResource) {
	    	ContactEntity contactEntity = new ContactEntity();
	    	contactEntity.setContactNumber(contactResource.getContactNumber());
	    	contactEntity.setContactType(contactResource.getContactType());
			
	        return contactEntity;
	    }
	};
	
	public CustomerEntity transformCustomer(CustomerResource customerResource){
		
		List<AddressEntity> addressEntities = customerResource.getAddresses().stream().
				map(transformAddressToEntity).collect(Collectors.toList());
		
		List<ContactEntity> contactEntities = customerResource.getContacts().stream().
				map(transformContactToEntity).collect(Collectors.toList());
		
		CustomerEntity customerEntity = transformCustomerToEntity.apply(customerResource);
		customerEntity.setAddress(addressEntities);
		customerEntity.setContacts(contactEntities);
		
		return customerEntity;
	}


}
