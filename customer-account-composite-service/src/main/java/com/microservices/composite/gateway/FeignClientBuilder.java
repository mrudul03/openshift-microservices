package com.microservices.composite.gateway;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Getter
public class FeignClientBuilder {
	
	@Value("${customer.api.url=http://customers-service:8080}")
    private String customerApiURL;
	
	@Value("${account.api.url=http://payment-account-service:8080}")
    private String accountApiURL;
	
	private PaymentAccountClient accountClient = createClient(PaymentAccountClient.class, accountApiURL);
	private CustomerClient customerClient = createClient(CustomerClient.class, customerApiURL);
	
	private static <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(type))
            .logLevel(Logger.Level.FULL)
            .target(type, uri);
    }

}
