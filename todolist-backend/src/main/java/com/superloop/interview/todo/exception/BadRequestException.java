package com.superloop.interview.todo.exception;

/**
 * bad request exception
 * 
 * @title BadRequestException
 * @author David Ding
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -1745203169393779089L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}
}
