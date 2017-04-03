package com.aegon.booking.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.aegon.booking.model.Booking;

public interface BookingRepository extends Repository<Booking, Long>{
	   List<Booking> findByRoom_RoomId( long roomId );
	   
	   List<Booking> findByCustomer_CustomerId( long customerId );
	   
	    @Query("SELECT b FROM booking b WHERE room_id = :roomId AND (:from >= check_in AND :from < check_out OR :to > check_in AND :to <= check_out)")
	    List<Booking> findByAvailabilityByRoom(@Param("roomId") long roomId, @Param("from") LocalDate from, @Param("to") LocalDate to);
	    
	   Booking save(Booking booking);
}
