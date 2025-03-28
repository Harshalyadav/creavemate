package com.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.dto.AuthRequest;
import com.users.dto.SignupRequest;
import com.users.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
	private UserService userService;
	
	//customer sign up
	@PostMapping("/signup")
	public ResponseEntity<?> addNewCustomer(@RequestBody SignupRequest request)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.addNewCustomer(request));
	}
	//get user details by id
		@GetMapping("/{userId}")
		public ResponseEntity<?> getUserDetails(@PathVariable Long userId)
		{
			return ResponseEntity.ok(userService.getUserDetails(userId));
		}
		/*
		 * Desc - user sign in 
		 * URL - http://host:port/users/signin 
		 * Method - POST 
		 *  payload - Request DTO (email , pwd) 
		 *  success resp - user details dto 
		 *  err resp - api resp dto- err mesg
		 * 
		 */
		@PostMapping("/signin")
		public ResponseEntity<?> userSignIn(@RequestBody @Valid
				AuthRequest dto) {
			System.out.println("in user sign in " + dto);

			return ResponseEntity.ok(userService.signIn(dto));

		}

		
}
