package com.aegon.booking.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.booking.api.BookingService;
import com.aegon.booking.model.Booking;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
    public List<Booking> getBooking(@PathVariable("roomId") long roomId) {
            return bookingService.getBookingsForRoom(roomId);
    }	
}
