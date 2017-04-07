package com.aegon.webservice.booking.web;

import java.time.LocalDate;

public class BookingRequest {
	private long roomId;
	private long customerId;
	private long establishmenId;
	private LocalDate checkIn;
	private LocalDate checkOut;

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
	public long getEstablishmenId() {
		return establishmenId;
	}

	/**
	 * @param establishmenId
	 *            the establishmenId to set
	 */
	public void setEstablishmenId(long establishmenId) {
		this.establishmenId = establishmenId;
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
