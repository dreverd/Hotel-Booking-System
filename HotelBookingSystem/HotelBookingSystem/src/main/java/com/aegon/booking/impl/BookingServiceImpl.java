package com.aegon.booking.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.booking.api.BookingService;
import com.aegon.booking.dao.BookingRepository;
import com.aegon.booking.model.Booking;

@Component("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Booking> getBookingsForRoom(long roomId) {
        return bookingRepository.findByRoom_RoomId(roomId);
    }

	@Override
	public List<Booking> getBookingsForCustomer(long customerId) {
		List<Booking> bookings = bookingRepository.findByCustomer_CustomerId(customerId);
        return bookings;
    }
	
	@Override
	public List<Booking> getAvailabilityForRoom(long roomId, Date from, Date to) {
		List<Booking> bookings = bookingRepository.findByAvailabilityByRoom(roomId, from, to);
		
		
        return bookings;		
	}
	
	@Override
	public Booking addBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
}
