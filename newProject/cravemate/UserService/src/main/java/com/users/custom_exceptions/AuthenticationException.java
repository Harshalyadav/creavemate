package com.users.custom_exceptions;

public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String errMesg) {
		super(errMesg);
	}
}
