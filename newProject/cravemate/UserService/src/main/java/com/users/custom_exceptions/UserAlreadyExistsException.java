package com.users.custom_exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String mesg) {
		super(mesg);
	}
}
