package com.aegon.webservice.booking.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.aegon.webservice.booking.BookingTestDataFactory;
import com.aegon.webservice.booking.api.BookingService;
import com.aegon.webservice.booking.model.Booking;
import com.aegon.webservice.errors.NoAvailabilityException;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(BookingServiceImpl.class)
public class BookingServiceImplTest {

	private BookingTestDataFactory factory;
	
	private static final long ROOM_ID = 1;
	private static final long CUSTOMER_ID = 1;

	@Autowired
    TestEntityManager entityManager;

	@Autowired
	private BookingService bookingService;
    
	@Before
	public void setUp() throws Exception {
		factory = new BookingTestDataFactory();
	}

	@Test
    public void shouldStoreBooking() throws NoAvailabilityException {
        long bookingId = bookingService.addBooking(factory.getBooking(0));

        List<Booking> foundBooking = bookingService.getBookingsForRoom(ROOM_ID);
        assertEquals(foundBooking.size(), 1);
        assertEquals(foundBooking.get(0).getBookingId(), bookingId);
    }
    
	@Test
    public void shouldThrowNoAvailabilityException() {
        try {
			bookingService.addBooking(factory.getBooking(1));
	        bookingService.addBooking(factory.getBooking(2));
	        fail("Expected NoAvailabilityException");
		} catch (NoAvailabilityException e) {
			assertEquals(e.getMessage(),
					"No availabilty on: " + Stream.iterate(LocalDate.now(), date -> date.plusDays(1)).limit(3)
							.map(day -> day.toString()).collect(Collectors.joining(",")));
		}
    }
    
    @Test
    public void shouldFailWhenLookingForNonExistingBooking() {
        List<Booking> foundBooking = bookingService.getBookingsForRoom(ROOM_ID);
        assertEquals(foundBooking.size(), 0);
    }

    @Test
    public void shouldFindBookingByRoomId() {
        Booking booking = factory.getBooking(0);
        entityManager.persist(booking);

        List<Booking> foundBooking = bookingService.getBookingsForRoom(ROOM_ID);

        assertEquals(Lists.newArrayList(booking), foundBooking);
    }

	@Test
    public void shouldFindBookingByCustomerId() {
        Booking booking = factory.getBooking(0);
        entityManager.persist(booking);

        List<Booking> foundBooking = bookingService.getBookingsForCustomer(CUSTOMER_ID);

        assertEquals(Lists.newArrayList(booking), foundBooking);
    }

	@Test
    public void shouldFindAvailabilityByRoomId() {
        List<LocalDate> foundBooking = bookingService.getAvailabilityForRoom(ROOM_ID,  LocalDate.now().plusDays(3), LocalDate.now().plusDays(6));

        assertEquals(factory.getAvailability(LocalDate.now().plusDays(3), 3), foundBooking);
    }

	@Test
    public void shouldFindPartialAvailabilityByRoomId() {
        Booking booking = factory.getBooking(0);
        entityManager.persist(booking);

        List<LocalDate> foundBooking = bookingService.getAvailabilityForRoom(ROOM_ID,  LocalDate.now().plusDays(2), LocalDate.now().plusDays(5));

        assertEquals(factory.getAvailability(LocalDate.now().plusDays(3), 2), foundBooking);
    }

	@Test
    public void shouldFindNoAvailabilityByRoomId() {
        Booking booking = factory.getBooking(0);
        entityManager.persist(booking);

        List<LocalDate> foundBooking = bookingService.getAvailabilityForRoom(ROOM_ID,  LocalDate.now(), LocalDate.now().plusDays(3));

        assertEquals(foundBooking.size(), 0);
    }
}
