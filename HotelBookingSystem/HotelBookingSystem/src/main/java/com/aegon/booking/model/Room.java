package com.aegon.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {

    @Id @GeneratedValue
    private long roomId;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;

    public long getRoomId() {
        return roomId;
    }

    void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
    	return getRoomId() + "," + getName() + "," + getDescription();
    }
}
