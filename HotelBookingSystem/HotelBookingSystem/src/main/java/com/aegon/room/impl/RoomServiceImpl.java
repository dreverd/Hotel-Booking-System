package com.aegon.room.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.room.api.RoomService;
import com.aegon.room.dao.RoomRepository;
import com.aegon.room.model.Room;

@Component("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Room getRoom(long roomId) {
        return roomRepository.findByRoomId(roomId);
    }
}
