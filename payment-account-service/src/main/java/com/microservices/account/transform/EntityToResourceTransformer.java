package com.microservices.account.transform;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.microservices.account.contract.PaymentAccountResource;
import com.microservices.account.repository.PaymentAccountEntity;

@Component
public class EntityToResourceTransformer {
	private Function<PaymentAccountEntity, PaymentAccountResource> transformAccountToResource = new 
			Function<PaymentAccountEntity, PaymentAccountResource>() {
		
	    public PaymentAccountResource apply(PaymentAccountEntity accountEntity) {
	    	PaymentAccountResource accountResource = new PaymentAccountResource();
	    	accountResource.setCustomerId(accountEntity.getCustomerId());
	    	accountResource.setAccountName(accountEntity.getAccountName());
	    	accountResource.setAccountNumber(accountEntity.getAccountNumber());
	    	accountResource.setBalance(accountEntity.getBalance());
	        return accountResource;
	    }
	};
	
	public PaymentAccountResource transformAccount(PaymentAccountEntity accountEntity){
		PaymentAccountResource accountResource = transformAccountToResource.apply(accountEntity);
		return accountResource;
	}
	
	public List<PaymentAccountResource> transformAccountList(List<PaymentAccountEntity> accounts){
		List<PaymentAccountResource> accountResources = accounts.stream().
				map(entity -> transformAccount(entity)).
				collect(Collectors.toList());
		
		return accountResources;
	}

}
