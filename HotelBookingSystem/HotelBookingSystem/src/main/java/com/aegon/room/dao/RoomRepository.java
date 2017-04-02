package com.aegon.room.dao;

import org.springframework.data.repository.Repository;

import com.aegon.room.model.Room;

public interface RoomRepository extends Repository<Room, Long>{
	   Room findByRoomId( long roomId );
}
