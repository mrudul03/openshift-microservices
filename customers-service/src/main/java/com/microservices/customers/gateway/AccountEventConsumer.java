package com.microservices.customers.gateway;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AccountEventConsumer {
//	private static final Logger LOGGER = LoggerFactory.getLogger(AccountEventConsumer.class);
//
//	private CountDownLatch latch = new CountDownLatch(1);
//
//	public CountDownLatch getLatch() {
//		return latch;
//	}
//
//	@KafkaListener(topics = "${kafka.topic.customers}")
//	public void receive(ConsumerRecord<?, ?> consumerRecord) {
//		LOGGER.info("received payload='{}'", consumerRecord.toString());
//		System.out.println("Received message ####################:"+consumerRecord.toString());
//		latch.countDown();
//	}

}
