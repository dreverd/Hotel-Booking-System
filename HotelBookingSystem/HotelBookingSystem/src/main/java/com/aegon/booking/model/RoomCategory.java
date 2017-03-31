package com.aegon.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="room_category")
public class RoomCategory {

	@Id @GeneratedValue
	private long roomCategoryId;
	
	@Enumerated(EnumType.STRING)
	private RoomCategoryType roomCategory;
	
	private int price;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="roomCategory")
    @JsonIgnore
	private List<Room> rooms;

	/**
	 * @return the roomCategoryId
	 */
	public long getRoomCategoryId() {
		return roomCategoryId;
	}

	/**
	 * @param roomCategoryId the roomCategoryId to set
	 */
	public void setRoomCategoryId(long roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	/**
	 * @return the roomCategory
	 */
	public RoomCategoryType getRoomCategory() {
		return roomCategory;
	}

	/**
	 * @param roomCategory the roomCategory to set
	 */
	public void setRoomCategory(RoomCategoryType roomCategory) {
		this.roomCategory = roomCategory;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the rooms
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
