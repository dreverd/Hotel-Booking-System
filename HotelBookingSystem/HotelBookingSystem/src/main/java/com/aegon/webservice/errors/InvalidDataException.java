package com.aegon.webservice.errors;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends Exception implements ExceptionHttpStatus {
	private static final long serialVersionUID = 2L;
	private String errorMessage;

	public String getErrorMessage() {
        return errorMessage;
    }

	public InvalidDataException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

	public InvalidDataException() {
        super();
    }

	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
