package com.microservices.composite.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.microservices.composite.contract.CustomerAccountResource;
import com.microservices.composite.contract.CustomerResource;
import com.microservices.composite.contract.PaymentAccountResource;
import com.microservices.composite.gateway.CustomerClient;
import com.microservices.composite.gateway.PaymentAccountClient;
import com.microservices.composite.transform.CustomerCompositeTransformer;

@Service
public class CustomerAccountService {
	
	private final PaymentAccountClient paymentAccountClient;
	private final CustomerClient customerClient;
	private final CustomerCompositeTransformer compositeTransformer;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
//	private final RestTemplate restTemplate;
//	private final String accountUrl = "http://payment-account-service:8080";
//	private final String customerUrl = "http://customer-service:8080";
	
	public CustomerAccountService(
			CustomerCompositeTransformer compositeTransformer,
			PaymentAccountClient paymentAccountClient,
			CustomerClient customerClient){
		
		this.compositeTransformer = compositeTransformer;
		this.paymentAccountClient = paymentAccountClient;
		this.customerClient = customerClient;
	}

	public CustomerAccountResource getCustomerAccountDetails(Long customerId){
		logger.info("In getCustomerAccountDetails for customer id:"+customerId);
		CustomerResource customerResource = customerClient.getCustomer(customerId);
		List<PaymentAccountResource> paymentAccounts = paymentAccountClient.getAllAccounts(customerId);
		return compositeTransformer.transformCustomerAccount(paymentAccounts, customerResource);
	}
	
//	public CustomerAccountResource getCustomerAccountDetails(Long customerId){
//		
//		CustomerResource customerResource = this.getCustomerResource(customerId);
//		List<PaymentAccountResource> paymentAccounts = this.getAccountsResource(customerId);
//		CustomerAccountResource customerAccountResource = compositeTransformer.transformCustomerAccount(paymentAccounts, customerResource);
//		return customerAccountResource;
//	}
	
//	private CustomerResource getCustomerResource(Long customerId){
//		CustomerResource customerResource = new CustomerResource();
//		try {
//			ResponseEntity<CustomerResource> customerResponse =
//	                restTemplate.exchange(customerUrl+"/customers/"+customerId, HttpMethod.GET, new HttpEntity<>(""), CustomerResource.class);
//			customerResource = customerResponse.getBody();
//		}
//		catch (HttpStatusCodeException ex) {
//            logger.info("Exception trying to get the response from Customer service.", ex);
//        } catch (RestClientException ex) {
//            logger.info("Exception trying to get the response from Customer service.", ex);
//        }
//		return customerResource;
//	}
//	
//	private List<PaymentAccountResource> getAccountsResource(Long customerId){
//		List<PaymentAccountResource> paymentAccounts = new ArrayList();
//		try {
//			ResponseEntity<List> accountResponse =
//	                restTemplate.exchange(accountUrl+"/customers/"+customerId+"/accounts/", HttpMethod.GET, new HttpEntity<>(""), List.class);
//			paymentAccounts = accountResponse.getBody();
//		}
//		catch (HttpStatusCodeException ex) {
//            logger.info("Exception trying to get the response from PaymentAccount service.", ex);
//        } catch (RestClientException ex) {
//            logger.info("Exception trying to get the response from PaymentAccount service.", ex);
//        }
//		return paymentAccounts;
//	}
}
