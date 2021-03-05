package dev.alexandrevieira.sm.services.exceptions;

public class DuplicateEntryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateEntryException(String msg) {
		super(msg);
	}
	
	public DuplicateEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
