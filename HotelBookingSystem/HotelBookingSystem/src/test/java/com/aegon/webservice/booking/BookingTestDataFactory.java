package com.aegon.webservice.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aegon.webservice.booking.model.Booking;
import com.aegon.webservice.booking.web.BookingRequest;
import com.aegon.webservice.customer.model.Customer;
import com.aegon.webservice.establishment.model.Establishment;
import com.aegon.webservice.room.model.Room;
import com.aegon.webservice.room.model.RoomCategory;
import com.aegon.webservice.room.model.RoomCategoryType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BookingTestDataFactory {
	private static final long DEFAULT_ID = 1L;
	
	public String getBookingRequest() throws JsonProcessingException {
    	BookingRequest request = new BookingRequest(DEFAULT_ID, DEFAULT_ID, DEFAULT_ID,  LocalDate.now(), LocalDate.now().plusDays(3));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(request);
    }

    public Booking getBooking(long bookingId) {
		return new Booking(bookingId, getCustomer(DEFAULT_ID), getRoom(DEFAULT_ID), getEstablishment(), LocalDate.now(), LocalDate.now().plusDays(3));
	}
	
    public Booking getBooking() {
		return getBooking(DEFAULT_ID);
	}
	
	public List<Booking> getRoomBookings(int numBookings, long roomId) {
		List<Booking> bookings = new ArrayList<>();
		for (int i = 1; i <= numBookings; ++i) {
			bookings.add(new Booking(i, getCustomer(i), getRoom(roomId), getEstablishment(), LocalDate.now(), LocalDate.now().plusDays(3)));
		}
		return bookings;
	}
	
	public List<Booking> getCustomerBookings(int numBookings, long customerId) {
		List<Booking> bookings = new ArrayList<>();
		for (int i = 1; i <= numBookings; ++i) {
			bookings.add(new Booking(i, getCustomer(customerId), getRoom(i), getEstablishment(), LocalDate.now(), LocalDate.now().plusDays(3)));
		}
		return bookings;
	}
	
	public List<LocalDate> getAvailability(int numDays) {
		return getAvailability(LocalDate.now(), numDays);
	}
	
	public List<LocalDate> getAvailability(LocalDate from, int numDays) {
		return Stream.iterate(from, date -> date.plusDays(1)).limit(numDays).collect(Collectors.toList());
	}
	
	public Establishment getEstablishment() {
		return new Establishment(DEFAULT_ID, "Lochside House", "Lochside House Hotel description");
	}
	
	public Customer getCustomer(long customerId) {
		return new Customer(customerId, "firstname" + customerId, "surname" + customerId);
	}

	public RoomCategory getRoomCategory() {
		return new RoomCategory(DEFAULT_ID, RoomCategoryType.SINGLE, 100);
	}

	public Room getRoom(long roomId) {
		return new Room(roomId, "Single Room " +  roomId, "This is single room " + roomId, getEstablishment(), getRoomCategory());
	}
}