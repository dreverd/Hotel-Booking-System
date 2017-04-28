package com.aegon.webservice.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aegon.webservice.response.ResponseStatusType;
import com.aegon.webservice.response.ResponseWrapper;

@ControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(value = { NoAvailabilityException.class })
	protected ResponseEntity<ResponseWrapper<?>> noAvailabilityExceptionErrorHandler(NoAvailabilityException ex) {
		return new ResponseEntity<ResponseWrapper<?>>(
				new ResponseWrapper<>(null, ResponseStatusType.FAIL, ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<ResponseWrapper<?>> resourceNotFoundErrorHandler(ResourceNotFoundException ex) {
		return new ResponseEntity<ResponseWrapper<?>>(
				new ResponseWrapper<>(null, ResponseStatusType.FAIL, ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { InvalidDataException.class })
	protected ResponseEntity<ResponseWrapper<?>> invalidDataErrorHandler(InvalidDataException ex) {
		return new ResponseEntity<ResponseWrapper<?>>(
				new ResponseWrapper<>(null, ResponseStatusType.FAIL, ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
