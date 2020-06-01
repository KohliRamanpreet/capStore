package com.capstore.exception;

public class UserAlreadyExistException extends RuntimeException{
	public UserAlreadyExistException (String message) {
		super(message);
	}


}
