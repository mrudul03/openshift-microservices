package com.microservices.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccountEntity, Long>{
	
	public List<PaymentAccountEntity> findByCustomerId(Long customerId);
}
