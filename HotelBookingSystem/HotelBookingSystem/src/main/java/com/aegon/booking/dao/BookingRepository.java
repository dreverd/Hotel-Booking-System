package com.aegon.booking.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.aegon.booking.model.Booking;

public interface BookingRepository extends Repository<Booking, Long>{
	   List<Booking> findByRoom_RoomId( long roomId );
	   
	   List<Booking> findByCustomer_CustomerId( long customerId );
	   
	   Booking save(Booking booking);
}
