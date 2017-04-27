package com.aegon.webservice.booking.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

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

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(BookingServiceImpl.class)
public class BookingServiceImplTest {

	private BookingTestDataFactory factory;
	
	private static final long BOOKING_ID = 1;
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
    public void shouldStoreBooking() {
        long bookingId = bookingService.addBooking(factory.getBooking(0));

        List<Booking> foundBooking = bookingService.getBookingsForRoom(ROOM_ID);
        assertThat(foundBooking).hasSize(1);
    }
    
	@Test
    public void shouldFindBookingByRoomId() {
        Booking booking = factory.getBooking(0);
        entityManager.persist(booking);

        List<Booking> foundBooking = bookingService.getBookingsForRoom(ROOM_ID);

        assertThat(foundBooking).isEqualTo(Lists.newArrayList(booking));
    }

	@Test
    public void shouldFindBookingByCustomerId() {
        Booking booking = factory.getBooking(0);
        entityManager.persist(booking);

        List<Booking> foundBooking = bookingService.getBookingsForCustomer(CUSTOMER_ID);

        assertThat(foundBooking).isEqualTo(Lists.newArrayList(booking));
    }

	@Test
    public void shouldFindAvailabilityByRoomId() {
        Booking booking = factory.getBooking(0);
        entityManager.persist(booking);

        List<LocalDate> foundBooking = bookingService.getAvailabilityForRoom(ROOM_ID,  LocalDate.now(), LocalDate.now().plusDays(3));

        assertThat(foundBooking).isEqualTo(factory.getAvailability(3));
    }

    @Test
    public void shouldFailWhenLookingForNonExistingBooking() {
    }
}
