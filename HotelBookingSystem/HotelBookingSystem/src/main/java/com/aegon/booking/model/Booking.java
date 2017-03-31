package com.aegon.booking.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="booking")
public class Booking {
	@Id @GeneratedValue
	private long bookingId;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId")
	private Room room;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;
		
    @ManyToOne
    @JoinColumn(name="establishmentId")
    private Establishment establishment;
    
    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Temporal(TemporalType.DATE)
    private Date checkOut;
    
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
	public Date getCheckIn() {
		return checkIn;
	}


	/**
	 * @param checkIn the checkIn to set
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}


	/**
	 * @return the checkOut
	 */
	public Date getCheckOut() {
		return checkOut;
	}


	/**
	 * @param checkOut the checkOut to set
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}


	@Override
	public String toString() {
		return getBookingId() + "," + getRoom() + "," + getCustomer();
	}}
