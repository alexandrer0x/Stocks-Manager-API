package dev.alexandrevieira.sm.services.exceptions;

public class NegativePositionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NegativePositionException(String msg) {
		super(msg);
	}
	
	public NegativePositionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
