package com.aegon.booking.impl;

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

	public List<Booking> getBookingsForRoom(long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }
}
