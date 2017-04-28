package com.aegon.webservice.errors;

public class NoAvailabilityException extends Exception {
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
}
