package com.microservices.composite.gateway;

import lombok.Getter;

@Getter
public class FeignClientBuilder {
	
	//@Value("${customer.api.url=http://customer-service:8080}")
    private String customerApiURL = "http://customer-service:8080";
	
	//@Value("${account.api.url=http://payment-account-service:8080}")
    private String accountApiURL = "http://payment-account-service:8080";
	
//	private PaymentAccountClient accountClient = createClient(PaymentAccountClient.class, accountApiURL);
//	private CustomerClient customerClient = createClient(CustomerClient.class, customerApiURL);
	
//	private static <T> T createClient(Class<T> type, String uri) {
//        return Feign.builder()
//            .client(new OkHttpClient())
//            .encoder(new GsonEncoder())
//            .decoder(new GsonDecoder())
//            .logger(new Slf4jLogger(type))
//            .logLevel(Logger.Level.FULL)
//            .target(type, uri);
//    }

}
