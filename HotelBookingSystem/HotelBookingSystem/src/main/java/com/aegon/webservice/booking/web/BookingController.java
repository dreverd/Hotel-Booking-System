package com.aegon.webservice.booking.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.webservice.booking.api.BookingService;
import com.aegon.webservice.booking.model.Booking;
import com.aegon.webservice.customer.api.CustomerService;
import com.aegon.webservice.customer.model.Customer;
import com.aegon.webservice.errors.ResourceNotFoundException;
import com.aegon.webservice.room.api.RoomService;
import com.aegon.webservice.room.model.Room;


@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/room/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<List<BookingResponse>> getBookingsByRoom(@PathVariable long roomId) throws ResourceNotFoundException {
		List<Booking> bookings = bookingService.getBookingsForRoom(roomId);
		
		if (bookings.size() == 0) {
			throw new ResourceNotFoundException("No bookings for room: " + roomId);
		}
		
		return new ResponseEntity<List<BookingResponse>>(convertToDto(bookings), HttpStatus.OK);
    }	

	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public List<BookingResponse> getBookingsByCustomer(@PathVariable long customerId) {
		return convertToDto(bookingService.getBookingsForCustomer(customerId));
    }	
	
	@RequestMapping(value = "/room/{roomId}/availability/from/{from}/to/{to}", method = RequestMethod.GET)
    public List<LocalDate> getAvailabilityForRoom(@PathVariable long roomId, 
    										 @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from,
		 									 @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to ) {
		return bookingService.getAvailabilityForRoom(roomId, from, to);
    }	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addBooking(@RequestBody BookingRequest request) {
		Booking booking = convertToEntity(request);
		return new ResponseEntity<Booking>(bookingService.addBooking(booking), HttpStatus.OK);
	}
	
	private List<BookingResponse> convertToDto(List<Booking> bookings) {
        return bookings.stream()
                .map(booking -> new BookingResponse(booking))
                .collect(Collectors.toList());
	}

	private Booking convertToEntity(BookingRequest request) {
	    Room room;
		Customer customer;

		Booking booking = new Booking();
		booking.setCheckIn(request.getCheckIn());
		booking.setCheckOut(request.getCheckOut());
		
		if (request.getEstablishmenId() != 1) {
			
		}
		
	    if ((customer = customerService.getCustomer(request.getCustomerId())) != null) {
			booking.setCustomer(customer);
	    }
	    
	    if ((room = roomService.getRoom(request.getRoomId())) != null) {
			booking.setRoom(room);
	    }
	    
	    return booking;
	}
}