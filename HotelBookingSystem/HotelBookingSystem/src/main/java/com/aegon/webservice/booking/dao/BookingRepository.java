package com.aegon.webservice.booking.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.aegon.webservice.booking.model.Booking;

public interface BookingRepository extends Repository<Booking, Long>{
	   List<Booking> findByRoom_RoomId( long roomId );
	   
	   List<Booking> findByCustomer_CustomerId( long customerId );
	   
	   Booking save(Booking booking);
}
