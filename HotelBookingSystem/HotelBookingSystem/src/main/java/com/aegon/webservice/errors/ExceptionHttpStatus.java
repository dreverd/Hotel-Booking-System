package com.aegon.webservice.errors;

import org.springframework.http.HttpStatus;

public interface ExceptionHttpStatus {
	public HttpStatus getHttpStatus();
}
