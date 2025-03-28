package com.users.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity 
@Table(name = "users") 
@NoArgsConstructor

@Getter
@Setter
@ToString(callSuper = true, exclude = { "password"})

public class User extends BaseEntity {

	@Column(length = 20) 
	private String firstName;
	@Column(length = 20) 
	private String lastName;
	@Column(length = 25, unique = true) // adds unique constraint
	private String email;
	@Column(length = 20, nullable = false) // not null constraint
	private String password;
	private LocalDate dob;
	@Enumerated(EnumType.STRING) 
	@Column(length = 30) 
	private UserRole role;
	public User(String firstName, String lastName, String email, String password, LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dob = dob;
	}
	
}
