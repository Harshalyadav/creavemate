package com.cravemate.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")

public class User {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String fullname;

	    @Column(nullable = false, unique = true)
	    private String email;

	    @Column
	    private String password;

	    @ElementCollection
	    @CollectionTable(name = "user_addresses", joinColumns = @JoinColumn(name = "user_id"))
	    private List<Address> address = new ArrayList<>();

	    @ElementCollection
	    @CollectionTable(name = "user_phone_numbers", joinColumns = @JoinColumn(name = "user_id"))
	    private List<Long> phoneNumber = new ArrayList<>();

	    // Constructors, Getters, Setters
	    public User() {}

	    public User(String fullname, String email, String password, List<Address> address, List<Long> phoneNumber) {
	        this.fullname = fullname;
	        this.email = email;
	        this.password = password;
	        this.address = address;
	        this.phoneNumber = phoneNumber;
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getFullname() {
	        return fullname;
	    }

	    public void setFullname(String fullname) {
	        this.fullname = fullname;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public List<Address> getAddress() {
	        return address;
	    }

	    public void setAddress(List<Address> address) {
	        this.address = address;
	    }

	    public List<Long> getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(List<Long> phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    // Hash Password Before Saving
	    public void hashPassword() {
	        this.password = new BCryptPasswordEncoder().encode(this.password);
	    }

	    // JWT Token Generation
	    public String generateJwtToken(String secretKey) {
	        // Use a JWT library like io.jsonwebtoken (JJWT) for token generation
	        return Jwts.builder()
	                .setSubject(this.id.toString())
	                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
	                .compact();
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        User user = (User) o;
	        return id.equals(user.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
	}

	// Address as Embeddable Class
	@Embeddable
	class Address {

	    @Column
	    private String detail;

	    @Column
	    private String forPurpose;

	    public Address() {}

	    public Address(String detail, String forPurpose) {
	        this.detail = detail;
	        this.forPurpose = forPurpose;
	    }

	    public String getDetail() {
	        return detail;
	    }

	    public void setDetail(String detail) {
	        this.detail = detail;
	    }

	    public String getForPurpose() {
	        return forPurpose;
	    }

	    public void setForPurpose(String forPurpose) {
	        this.forPurpose = forPurpose;
	    }
}









