package com.aegon.booking.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.booking.api.BookingService;
import com.aegon.booking.model.Booking;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value = "/room/{roomId}", method = RequestMethod.GET)
    public List<Booking> getBookingsByRoom(@PathVariable long roomId) {
            return bookingService.getBookingsForRoom(roomId);
    }	

	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public List<Booking> getBookingsByCustomer(@PathVariable long customerId) {
            return bookingService.getBookingsForCustomer(customerId);
    }	
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> addBooking(@RequestBody Booking booking) {
		return new ResponseEntity<Booking>(bookingService.addBooking(booking), HttpStatus.OK);
	}
}
