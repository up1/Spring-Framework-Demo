package com.up1.demo.exception;

@SuppressWarnings("serial")
public class UnknownResourceException extends RuntimeException {
	public UnknownResourceException(String message) {
		super(message);
	}
}
