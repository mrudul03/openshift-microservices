package com.microservices.account.infrastructure.messaging;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.microservices.account.contract.PaymentAccountResource;
import com.microservices.account.domain.PaymentAccountService;

@Component
public class CustomerEventConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEventConsumer.class);
	
	@Autowired
	private PaymentAccountService paymentAccountService;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

//	@KafkaListener(topics = "customers.t")
//	public void receive(ConsumerRecord<?, ?> consumerRecord) {
//		LOGGER.info("received payload='{}'", consumerRecord.toString());
//		System.out.println("Received message ####################:"+consumerRecord.toString());
//		PaymentAccountResource accountResource = new PaymentAccountResource(
//				Long.valueOf(12), "Acc 12", "Acc 12", 123.00);
//		paymentAccountService.createPaymentAccount(accountResource);
//		System.out.println("Invoked create account ####################:");
//		latch.countDown();
//	}
	
	@KafkaListener(topics = "customers.t")
	public void receive(CreatePaymentAccountCommand paymentAccountCommand) {
		LOGGER.info("received CreatePaymentAccountCommand");
		try {
			LOGGER.info("received CreatePaymentAccountCommand='{}'", paymentAccountCommand.toString());
			PaymentAccountResource accountResource = new PaymentAccountResource(
					paymentAccountCommand.getCustomerId(), 
					"AccName-"+paymentAccountCommand.getCustomerId(), 
					"Acc-"+paymentAccountCommand.getCustomerId(), 
					123.00);
			
			paymentAccountService.createPaymentAccount(accountResource);
			System.out.println("Invoked create account ####################:");
			latch.countDown();
		}
		catch(Throwable t){
			LOGGER.error("Exception at receive:", t);
		}
		
	}

}
