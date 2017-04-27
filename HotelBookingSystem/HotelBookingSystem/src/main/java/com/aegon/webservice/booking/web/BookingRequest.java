package com.aegon.webservice.booking.web;

import java.time.LocalDate;

public class BookingRequest {
	private long roomId;
	private long customerId;
	private long establishmentId;
	private LocalDate checkIn;
	private LocalDate checkOut;

	public BookingRequest() {}
	
	public BookingRequest(long roomId, 
						  long customerId, 
						  long establishmentId, 
						  LocalDate checkIn, 
						  LocalDate checkOut) {
		this.roomId = roomId;
		this.customerId = customerId;
		this.establishmentId = establishmentId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	/**
	 * @return the roomId
	 */
	public long getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the establishmenId
	 */
	public long getEstablishmentId() {
		return establishmentId;
	}

	/**
	 * @param establishmenId
	 *            the establishmenId to set
	 */
	public void setEstablishmentId(long establishmentId) {
		this.establishmentId = establishmentId;
	}

	/**
	 * @return the checkIn
	 */
	public LocalDate getCheckIn() {
		return checkIn;
	}

	/**
	 * @param checkIn
	 *            the checkIn to set
	 */
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * @return the checkOut
	 */
	public LocalDate getCheckOut() {
		return checkOut;
	}

	/**
	 * @param checkOut
	 *            the checkOut to set
	 */
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

}
