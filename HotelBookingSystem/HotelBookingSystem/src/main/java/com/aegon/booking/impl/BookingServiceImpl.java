package com.aegon.booking.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.booking.api.BookingService;
import com.aegon.booking.dao.BookingRepository;
import com.aegon.booking.model.Booking;

@Component("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Booking> getBookingsForRoom(long roomId) {
        return bookingRepository.findByRoom_RoomId(roomId);
    }

	@Override
	public List<Booking> getBookingsForCustomer(long customerId) {
		List<Booking> bookings = bookingRepository.findByCustomer_CustomerId(customerId);
        return bookings;
    }
	
	@Override
	public List<LocalDate> getAvailabilityForRoom(long roomId, LocalDate from, LocalDate to) {
		List<LocalDate> days = null;
		List<Booking> bookings = bookingRepository.findByAvailabilityByRoom(roomId, from, to);

		if (bookings != null) {
			days = Stream.iterate(from, date -> date.plusDays(1)).limit(DAYS.between(from, to)).collect(Collectors.toList());		
			days.stream().filter(day -> inBookingPeriod(day, bookings)).collect(Collectors.toList());
		}
		
        return days;		
	}
	
	@Override
	public Booking addBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	private static boolean inBookingPeriod(LocalDate day, List<Booking> bookings) {
		for(Booking booking: bookings) {
			if (day.isBefore(booking.getCheckIn()) && day.isAfter(booking.getCheckOut())) {
				return false;
			}
		}
		
		return true;
	}
}
