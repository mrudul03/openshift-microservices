package com.microservices.customers.repository;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AddressEntity {
	
	@Column(name="STREET_ADDRESS")
    private String streetAddress;
    
    @Column(name="STATE")
    private String state;
    
    @Column(name="CITY")
    private String city;
    
    @Column(name="ZIP_CODE")
    private String areaCode;
    
    @Column(name="ADDR_TYPE")
    private String addressType;

}
