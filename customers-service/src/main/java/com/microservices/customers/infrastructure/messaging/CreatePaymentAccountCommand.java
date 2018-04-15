package com.microservices.customers.infrastructure.messaging;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentAccountCommand {
	
	private Long customerId;
	private String firstName;
	private String lastName;
	
	@Override
	public String toString() {
		return "[CreatePaymentAccountCommand[customerId="+customerId+
				" firstName="+firstName+
				" lastName="+lastName+"]]";
	}
	
}
