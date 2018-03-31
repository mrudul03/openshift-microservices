package com.microservices.customers.repository;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ContactEntity {
	
	@Column(name="CONTACT_TYPE")
	private String contactType;
	
	@Column(name="CONTACT_NO")
	private String contactNumber;
}
