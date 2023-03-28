package com.realworld.backend.api.exception;

public class InvalidAuthenticationException extends RuntimeException {

	public InvalidAuthenticationException() {
		super("invalid email or password");
	}
}
