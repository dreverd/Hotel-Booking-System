package com.aegon.webservice.booking.api;

import java.time.LocalDate;
import java.util.List;

import com.aegon.webservice.booking.model.Booking;

public interface BookingService {

	/**
	 * Retrieve bookings against a room
	 * @param roomId
	 * @return a list of bookings for the given room id
	 */
	public List<Booking> getBookingsForRoom(long roomId);

	/**
	 * Retrieve bookings against a customer
	 * @param customerId
	 * @return a list of bookings for the given customer id
	 */
	public List<Booking> getBookingsForCustomer(long customerId);
	
	/**
	 * Get the availability for the given room
	 * @param roomId
	 * @param from
	 * @param to
	 * @return available dates
	 */
	public List<LocalDate> getAvailabilityForRoom(long roomId, LocalDate from, LocalDate to);
	
	/**
	 * Retrieve availability for a room over a given period
	 * @param roomId
	 * @param from
	 * @param to
	 * @return 
	 */
//	public List<Booking> getAvailability(long roomId, Date from, Date to);
	
	/**
	 * Add a new booking
	 * @param booking to add
	 * @return the newly added booking
	 */
	public long addBooking(Booking booking);
}
