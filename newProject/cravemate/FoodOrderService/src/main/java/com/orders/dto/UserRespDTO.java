package com.orders.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRespDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dob;	
	private UserRole role;
}
