package com.aegon.webservice.errors;

import org.springframework.http.HttpStatus;

public class NoAvailabilityException extends Exception implements ExceptionHttpStatus {
	private static final long serialVersionUID = 1L;
    private String errorMessage;

	public String getErrorMessage() {
        return errorMessage;
    }

	public NoAvailabilityException(String unavailabledays) {
        super(unavailabledays);
        this.errorMessage = unavailabledays;
    }

	public NoAvailabilityException() {
        super();
    }
	
	public HttpStatus getHttpStatus() {
		return HttpStatus.OK;
	}
}
