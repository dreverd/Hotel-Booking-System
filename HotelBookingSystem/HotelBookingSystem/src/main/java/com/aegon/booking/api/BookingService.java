package com.aegon.booking.api;

import java.util.List;

import com.aegon.booking.model.Booking;

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
	 * Add a new booking
	 * @param booking to add
	 * @return the newly added booking
	 */
	public Booking addBooking(Booking booking);
}
