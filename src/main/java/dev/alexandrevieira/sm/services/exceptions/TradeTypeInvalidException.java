package dev.alexandrevieira.sm.services.exceptions;

public class TradeTypeInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TradeTypeInvalidException(String msg) {
		super(msg);
	}
	
	public TradeTypeInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
