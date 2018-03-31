package com.microservices.customers.contract;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CustomerResource {
	
	private Long customerId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Date dateCreated;
	private Date dateUodated;
	private List<AddressResource> addresses;
	private List<ContactResource> contacts;
}
