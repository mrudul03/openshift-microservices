package com.microservices.customers.contract;

import lombok.Data;

@Data
public class ContactResource {
	
	private String contactType;
	private String contactNumber;

}
