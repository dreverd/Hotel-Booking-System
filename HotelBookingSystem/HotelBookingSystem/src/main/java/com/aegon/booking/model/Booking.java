package com.aegon.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Booking {
	private long id;

    @Column(nullable = false)
	private long roomId;

    @Column(nullable = false)
    private String customerName;
	
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }	

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
	
    @Override
	public String toString() {
		return getId() + "," + getRoomId() + "," + getCustomerName();
	}}
