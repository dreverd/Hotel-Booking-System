package com.aegon.room.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.aegon.booking.model.Establishment;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="room")
public class Room {

    @Id @GeneratedValue
    private long roomId;

    private String name;
    
    private String description;

    @ManyToOne
    @JoinColumn(name="establishmentId")
    @JsonIgnore
    private Establishment establishment;

    @ManyToOne
    @JoinColumn(name="roomCategoryId")
    private RoomCategory roomCategory;
    
    /**
	 * @return the roomId
	 */
	public long getRoomId() {
		return roomId;
	}


	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the roomCategory
	 */
	public RoomCategory getRoomCategory() {
		return roomCategory;
	}


	/**
	 * @param roomCategory the roomCategory to set
	 */
	public void setRoomCategory(RoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}


	@Override
    public String toString() {
    	return getRoomId() + "," + getName() + "," + getDescription();
    }
}