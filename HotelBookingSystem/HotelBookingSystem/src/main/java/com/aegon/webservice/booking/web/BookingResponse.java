package com.aegon.webservice.booking.web;

import java.time.LocalDate;

import com.aegon.webservice.booking.model.Booking;
import com.aegon.webservice.customer.model.Customer;
import com.aegon.webservice.room.model.Room;

public class BookingResponse {
	private long bookingId;
	private Room room;
    private Customer customer;
    
    private LocalDate checkIn;
    private LocalDate checkOut;
    
    public BookingResponse(Booking booking) {
    	this.bookingId = booking.getBookingId();
    	this.customer = booking.getCustomer();
    	this.room = booking.getRoom();
    	this.checkIn = booking.getCheckIn();
    	this.checkOut = booking.getCheckOut();
    }
    
    /**
	 * @return the bookingId
	 */
	public long getBookingId() {
		return bookingId;
	}


	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}


	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}


	/**
	 * @return the checkIn
	 */
	public LocalDate getCheckIn() {
		return checkIn;
	}


	/**
	 * @return the checkOut
	 */
	public LocalDate getCheckOut() {
		return checkOut;
	}	
}
