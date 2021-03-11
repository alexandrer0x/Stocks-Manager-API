package dev.alexandrevieira.sm.services.exceptions;

public class ServiceUnavaliableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceUnavaliableException(String msg) {
		super(msg);
	}
	
	public ServiceUnavaliableException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
