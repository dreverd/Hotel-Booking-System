package com.aegon.webservice.booking.service;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.webservice.booking.api.BookingService;
import com.aegon.webservice.booking.dao.BookingRepository;
import com.aegon.webservice.booking.model.Booking;
import com.aegon.webservice.errors.NoAvailabilityException;

@Component("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public long addBooking(Booking booking) throws NoAvailabilityException {
		List<LocalDate> unavailabledays = getDaysInPeriod(booking.getCheckIn(), booking.getCheckOut()).stream()
				.filter(day -> inBookingPeriod(day, getBookingsForRoom(booking.getRoom().getRoomId())))
				.collect(Collectors.toList());

		if (unavailabledays != null && unavailabledays.size() > 0) {
			throw new NoAvailabilityException("No availabilty on: "
					+ unavailabledays.stream().map(day -> day.toString()).collect(Collectors.joining(",")));
		}

		return bookingRepository.save(booking).getBookingId();
	}
	
	@Override
	public List<Booking> getBookingsForRoom(long roomId) {
        return bookingRepository.findByRoom_RoomId(roomId);
    }

	@Override
	public List<Booking> getBookingsForCustomer(long customerId) {
		return bookingRepository.findByCustomer_CustomerId(customerId);
    }
	
	@Override
	public List<LocalDate> getAvailabilityForRoom(long roomId, LocalDate from, LocalDate to) {
		return getDaysInPeriod(from, to).stream().filter(day -> !inBookingPeriod(day, getBookingsForRoom(roomId)))
				.collect(Collectors.toList());
	}

	private List<LocalDate> getDaysInPeriod(LocalDate from, LocalDate to) {
		return Stream.iterate(from, date -> date.plusDays(1)).limit(DAYS.between(from, to)).collect(Collectors.toList());		 
	}
	
	private static boolean inBookingPeriod(LocalDate day, List<Booking> bookings) {
		if (bookings != null) {
			for(Booking booking: bookings) {
				if (day.isAfter(booking.getCheckIn().minusDays(1)) && day.isBefore(booking.getCheckOut())) {
					return true;
				}
			}
		}
		
		return false;
	}
}
