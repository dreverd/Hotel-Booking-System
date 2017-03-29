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
}
