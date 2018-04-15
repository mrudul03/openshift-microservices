package com.microservices.customers.infrastructure.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;

@Component
public class CustomerEventPublisher {

	@Autowired
	private KafkaTemplate<String, CreatePaymentAccountCommand> kafkaTemplate;
	
	//@Value("${kafka.topic.json}")
	private String jsonTopic = "customers.t";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEventPublisher.class);

//	public void send(String topic, String payload) {
//		LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
//		System.out.println("Sending message ####################:"+payload);
//		kafkaTemplate.send(topic, payload);
//	}
	
	public void sendCustomerData(CreatePaymentAccountCommand payload) {
		LOGGER.info("sending payload='{}' to topic='{}'", payload);
		System.out.println("Sending message ####################:"+payload);
		kafkaTemplate.send(jsonTopic, payload);
	}

}
