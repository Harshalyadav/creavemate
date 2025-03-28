package com.orders.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "delivery_address")
public class DeliveryAddress  extends BaseEntity  {
	private String line1;
	private String line2;
	private String city;
	private String country;
	private String zipCode;
	private String phoneNo;
}
