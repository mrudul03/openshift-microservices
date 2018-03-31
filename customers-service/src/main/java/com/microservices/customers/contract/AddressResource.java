package com.microservices.customers.contract;

import lombok.Data;

@Data
public class AddressResource {
	
	private String streetAddress;
    private String state;
    private String city;
    private String areaCode;
    private String addressType;
}
