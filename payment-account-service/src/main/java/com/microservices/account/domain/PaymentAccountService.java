package com.microservices.account.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.account.repository.PaymentAccountRepository;

@Service
public class PaymentAccountService {
	
	@Autowired
	private PaymentAccountRepository paymentAccountRepository;
	
	public PaymentAccount getPaymentAccount(){
		return new PaymentAccount("MyAccount", "Acc-123", 123.00);
	}

}
