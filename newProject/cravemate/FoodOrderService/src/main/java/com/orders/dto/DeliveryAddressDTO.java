package com.orders.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryAddressDTO {
	private String line1;
	private String line2;
	private String city;
	private String country;
	private String zipCode;
	private String phoneNo;
}
