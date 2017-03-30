package com.aegon.booking.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Booking {
	@Id @GeneratedValue
	private long bookingId;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId")
	private Room room;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;
	
    public long getBookingId() {
        return bookingId;
    }

    void setBookingId(long id) {
        this.bookingId = id;
    }	

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomerId(Customer customer) {
        this.customer = customer;
    }
	
    @Override
	public String toString() {
		return getBookingId() + "," + getRoom() + "," + getCustomer();
	}}
