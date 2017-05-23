package com.aegon.webservice.room.repository;

import org.springframework.data.repository.Repository;

import com.aegon.webservice.room.model.Room;

public interface RoomRepository extends Repository<Room, Long>{
	   Room findByRoomId( long roomId );
}
