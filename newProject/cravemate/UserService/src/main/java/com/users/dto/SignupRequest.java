package com.users.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import com.users.entities.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupRequest {
	@NotBlank
	private String firstName;
	private String lastName;
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotNull
	@Past
	private LocalDate dob;
}
