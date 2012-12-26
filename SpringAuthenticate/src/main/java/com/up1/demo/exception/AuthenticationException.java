package com.up1.demo.exception;

@SuppressWarnings("serial")
public class AuthenticationException extends Exception {
	public AuthenticationException(String message) {
		super(message);
	}

}
