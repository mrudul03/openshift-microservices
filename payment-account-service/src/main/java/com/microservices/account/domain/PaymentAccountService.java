package com.microservices.account.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.account.contract.PaymentAccountResource;
import com.microservices.account.repository.PaymentAccountEntity;
import com.microservices.account.repository.PaymentAccountRepository;
import com.microservices.account.transform.ResourceToEntityTransformer;

@Service
@Transactional
public class PaymentAccountService {
	
	@Autowired
	private PaymentAccountRepository paymentAccountRepository;
	
	@Autowired
	private ResourceToEntityTransformer resourceToEntityTransformer;
	
	public PaymentAccountEntity getPaymentAccount(Long id){
		return paymentAccountRepository.getOne(id);
	}
	
	public List<PaymentAccountEntity> getPaymentAccounts(Long id){
		return paymentAccountRepository.findAll();
	}
	
	public List<PaymentAccountEntity> getPaymentAccountsByCustomer(Long customerId){
		return paymentAccountRepository.findByCustomerId(customerId);
	}
	
	public PaymentAccountEntity createPaymentAccount(PaymentAccountResource accountResource){
		System.out.println("Creating customer on controller req #######################");
		PaymentAccountEntity accountEntity = resourceToEntityTransformer.transformAccount(accountResource);
		paymentAccountRepository.save(accountEntity);
		System.out.println("Saved account to DB #######################");
		return accountEntity;
	}
	

}
