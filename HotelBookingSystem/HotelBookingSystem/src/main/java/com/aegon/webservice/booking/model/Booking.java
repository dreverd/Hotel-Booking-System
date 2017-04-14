package com.aegon.webservice.booking.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.aegon.webservice.customer.model.Customer;
import com.aegon.webservice.establishment.model.Establishment;
import com.aegon.webservice.room.model.Room;


@Entity(name="booking")
public class Booking {
	@Id @GeneratedValue
	private long bookingId;

	@OneToOne
    @JoinColumn(name = "roomId")
	private Room room;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
		
    @ManyToOne
    @JoinColumn(name="establishmentId")
    private Establishment establishment;
    
    private LocalDate checkIn;

    private LocalDate checkOut;
    
    /**
	 * @return the bookingId
	 */
	public long getBookingId() {
		return bookingId;
	}


	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}


	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}


	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}


	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}


	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	/**
	 * @return the establishment
	 */
	public Establishment getEstablishment() {
		return establishment;
	}


	/**
	 * @param establishment the establishment to set
	 */
	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}


	/**
	 * @return the checkIn
	 */
	public LocalDate getCheckIn() {
		return checkIn;
	}


	/**
	 * @param checkIn the checkIn to set
	 */
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}


	/**
	 * @return the checkOut
	 */
	public LocalDate getCheckOut() {
		return checkOut;
	}


	/**
	 * @param checkOut the checkOut to set
	 */
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}


	@Override
	public String toString() {
		return getBookingId() + "," + getRoom() + "," + getCustomer();
	}}
