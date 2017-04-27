package com.aegon.webservice.booking.web;

import java.time.LocalDate;
import java.util.List;

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
import com.aegon.webservice.errors.InvalidDataException;
import com.aegon.webservice.errors.ResourceNotFoundException;
import com.aegon.webservice.establishment.api.EstablishmentService;
import com.aegon.webservice.establishment.model.Establishment;
import com.aegon.webservice.response.ResponseStatusType;
import com.aegon.webservice.response.ResponseWrapper;
import com.aegon.webservice.room.api.RoomService;
import com.aegon.webservice.room.model.Room;

@RestController
@RequestMapping("/bookings")
public class BookingRestController extends BaseRestController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EstablishmentService establishmentService;
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/room/{roomId}", method = RequestMethod.GET)
    public ResponseWrapper<List<BookingResponse>> getBookingsByRoom(@PathVariable long roomId) throws ResourceNotFoundException {
		List<Booking> bookings = bookingService.getBookingsForRoom(roomId);
		return generateGetResponse(bookings, booking -> new BookingResponse(booking));
    }	

	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseWrapper<List<BookingResponse>> getBookingsByCustomer(@PathVariable long customerId) throws ResourceNotFoundException {
		List<Booking> bookings = bookingService.getBookingsForCustomer(customerId);
		return generateGetResponse(bookings, booking -> new BookingResponse(booking));
    }	
	
	@RequestMapping(value = "/room/{roomId}/availability/from/{from}/to/{to}", method = RequestMethod.GET)
    public ResponseWrapper<List<LocalDate>> getAvailabilityForRoom(@PathVariable long roomId, 
    										 @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from,
		 									 @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to ) throws ResourceNotFoundException {
		List<LocalDate> dates = bookingService.getAvailabilityForRoom(roomId, from, to);
		return generateGetResponse(dates);
    }	
	
	// TODO - sort out return
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseWrapper<Long>> addBooking(@RequestBody BookingRequest request)
			throws InvalidDataException {
		return new ResponseEntity<ResponseWrapper<Long>>(new ResponseWrapper<Long>(
				bookingService.addBooking(convertToEntity(request)), ResponseStatusType.SUCCESS), HttpStatus.CREATED);
	}
	
	protected Booking convertToEntity(BookingRequest request) throws InvalidDataException {
	    Room room;
		Customer customer;
		Establishment establishment;

		Booking booking = new Booking();
		booking.setCheckIn(request.getCheckIn());
		booking.setCheckOut(request.getCheckOut());
		
	    if ((establishment = establishmentService.getEstablishment(request.getEstablishmentId())) != null) {
			booking.setEstablishment(establishment);
	    } else {
	    	throw new InvalidDataException("No such establishment: " + request.getEstablishmentId());
	    }
		
	    if ((customer = customerService.getCustomer(request.getCustomerId())) != null) {
			booking.setCustomer(customer);
	    } else {
	    	throw new InvalidDataException("No such customer: " + request.getCustomerId());
	    }
	    
	    if ((room = roomService.getRoom(request.getRoomId())) != null) {
			booking.setRoom(room);
	    } else {
	    	throw new InvalidDataException("No such room: " + request.getRoomId());
	    }
	    
	    return booking;
	}
}
