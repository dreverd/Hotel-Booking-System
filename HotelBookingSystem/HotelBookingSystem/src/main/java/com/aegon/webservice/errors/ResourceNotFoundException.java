package com.aegon.webservice.errors;

public class ResourceNotFoundException extends Exception {
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
}
