package com.microservices.customers.infrastructure.messaging;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePaymentAccountCommand implements Serializable{
	
	private static final long serialVersionUID = -2080280831933248960L;
	
	private Long customerId;
	public String firstName;
	public String lastName;
	
}
