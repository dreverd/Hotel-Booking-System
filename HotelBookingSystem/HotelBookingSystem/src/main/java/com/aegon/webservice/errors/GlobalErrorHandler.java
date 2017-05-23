package com.aegon.webservice.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aegon.webservice.response.ResponseStatusType;
import com.aegon.webservice.response.ResponseWrapper;

@ControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(value = { NoAvailabilityException.class, 
								ResourceNotFoundException.class,
								InvalidDataException.class })
	protected <T extends Exception & ExceptionHttpStatus> ResponseEntity<ResponseWrapper<?>> globalExceptionErrorHandler(T ex) {
		return new ResponseEntity<ResponseWrapper<?>>(
				new ResponseWrapper<>(null, ResponseStatusType.FAIL, ex.getMessage()), ex.getHttpStatus());
	}
}
