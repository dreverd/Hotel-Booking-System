package com.aegon.booking.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="establishment")
public class Establishment {
	@Id @GeneratedValue
	private long establishmentId;

	private String name;

	private String description;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="establishment")
	@JsonIgnore
	private List<Booking> bookings;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="establishment")
	@JsonIgnore
	private List<Room> rooms;

	/**
	 * @return the establishmentId
	 */
	public long getEstablishmentId() {
		return establishmentId;
	}

	/**
	 * @param establishmentId the establishmentId to set
	 */
	public void setEstablishmentId(long establishmentId) {
		this.establishmentId = establishmentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the bookings
	 */
	public List<Booking> getBookings() {
		return bookings;
	}

	/**
	 * @param bookings
	 *            the bookings to set
	 */
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	/**
	 * @return the rooms
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms
	 *            the rooms to set
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
