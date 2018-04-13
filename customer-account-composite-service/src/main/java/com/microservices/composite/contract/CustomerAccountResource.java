package com.microservices.composite.contract;

import java.util.List;

import lombok.Data;

@Data
public class CustomerAccountResource {
	
	private Long customerId;
	private String firstName;
	private String lastName;
	private List<PaymentAccountResource> accounts;
	
}
