package com.aegon.webservice.booking.web;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aegon.webservice.booking.model.Booking;
import com.aegon.webservice.errors.InvalidDataException;
import com.aegon.webservice.errors.ResourceNotFoundException;
import com.aegon.webservice.response.ResponseStatusType;
import com.aegon.webservice.response.ResponseWrapper;

public abstract class BaseRestController {
	private <T,R> List<R> convertToDto(List<T> records, Function<T, R> mapper) {
        return records.stream()
                .map(mapper)
                .collect(Collectors.toList());
	}
	
	protected <T> ResponseEntity<ResponseWrapper<List<T>>> generateGetResponse(List<T> records)
			throws ResourceNotFoundException {
		if (records.size() == 0) {
			throw new ResourceNotFoundException("No resource found");
		}

		return new ResponseEntity<ResponseWrapper<List<T>>>(
				new ResponseWrapper<List<T>>(records, ResponseStatusType.SUCCESS), HttpStatus.OK);
	}

	protected <T, R> ResponseEntity<ResponseWrapper<List<R>>> generateGetResponse(List<T> records,
			Function<T, R> mapper) throws ResourceNotFoundException {
		return generateGetResponse(convertToDto(records, mapper));
	}
	
	protected abstract Booking convertToEntity(BookingRequest request) throws InvalidDataException;
}
