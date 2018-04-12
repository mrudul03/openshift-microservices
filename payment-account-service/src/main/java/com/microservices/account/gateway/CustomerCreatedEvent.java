package com.microservices.account.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerCreatedEvent {
	
	private final Long customerId;
    private final String customerName;
    
}
