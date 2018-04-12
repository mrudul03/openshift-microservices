package com.microservices.account.transform;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.microservices.account.contract.PaymentAccountResource;
import com.microservices.account.repository.PaymentAccountEntity;

@Component
public class ResourceToEntityTransformer {
	
	private Function<PaymentAccountResource, PaymentAccountEntity> transformAccountToEntity = new 
			Function<PaymentAccountResource, PaymentAccountEntity>() {
		
	    public PaymentAccountEntity apply(PaymentAccountResource accountResource) {
	    	PaymentAccountEntity accountEntity = new PaymentAccountEntity();
	    	accountEntity.setCustomerId(accountResource.getCustomerId());
	    	accountEntity.setAccountName(accountResource.getAccountName());
	    	accountEntity.setAccountNumber(accountResource.getAccountNumber());
	    	accountEntity.setBalance(accountResource.getBalance());
	        return accountEntity;
	    }
	};
	
	public PaymentAccountEntity transformAccount(PaymentAccountResource accountResource){
		PaymentAccountEntity accountEntity = transformAccountToEntity.apply(accountResource);
		return accountEntity;
	}


}
