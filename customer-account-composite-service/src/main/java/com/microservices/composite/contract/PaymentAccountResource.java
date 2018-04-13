package com.microservices.composite.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAccountResource {
	
	private Long customerId;
	private String accountName;
	private String accountNumber;
	private double balance;

}
