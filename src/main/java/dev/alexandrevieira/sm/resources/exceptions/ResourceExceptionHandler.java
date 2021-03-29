package dev.alexandrevieira.sm.resources.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.alexandrevieira.sm.services.exceptions.AuthorizationException;
import dev.alexandrevieira.sm.services.exceptions.BusinessRuleException;
import dev.alexandrevieira.sm.services.exceptions.DuplicateEntryException;
import dev.alexandrevieira.sm.services.exceptions.NegativePositionException;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;
import dev.alexandrevieira.sm.services.exceptions.ServiceUnavaliableException;
import dev.alexandrevieira.sm.services.exceptions.TradeTypeInvalidException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(status.value(), "Violação à restição do banco de dados.", LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<StandardError> duplicateEntry(DuplicateEntryException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(status.value(), "Violação à integridade dos dados.", LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<StandardError> missingServletRequestParameter(MissingServletRequestParameterException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ServiceUnavaliableException.class)
	public ResponseEntity<StandardError> serviceUnavaliable(ServiceUnavaliableException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(NegativePositionException.class)
	public ResponseEntity<StandardError> negativePosition(NegativePositionException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(TradeTypeInvalidException.class)
	public ResponseEntity<StandardError> tradeTypeInvalid(TradeTypeInvalidException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<StandardError> businessRule(BusinessRuleException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(status.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
}
