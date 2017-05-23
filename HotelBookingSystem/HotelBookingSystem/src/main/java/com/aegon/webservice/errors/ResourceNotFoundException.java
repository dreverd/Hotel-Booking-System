package com.aegon.webservice.errors;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends Exception implements ExceptionHttpStatus {
	private static final long serialVersionUID = 1L;
    private String errorMessage;

	public String getErrorMessage() {
        return errorMessage;
    }

	public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

	public ResourceNotFoundException() {
        super();
    }

	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
