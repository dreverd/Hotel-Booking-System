package com.aegon.webservice.booking.web;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aegon.webservice.booking.BookingTestDataFactory;
import com.aegon.webservice.booking.api.BookingService;
import com.aegon.webservice.booking.model.Booking;
import com.aegon.webservice.customer.api.CustomerService;
import com.aegon.webservice.establishment.api.EstablishmentService;
import com.aegon.webservice.room.api.RoomService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingRestController.class)
public class BookingRestControllerTest {

	private static final long BOOKING_ID = 1;
	private static final long ROOM_ID = 1;
	private static final long CUSTOMER_ID = 1;
	private static final long ESTABLISHMENT_ID = 1;
	
	private BookingTestDataFactory factory;
	
	@Autowired
    MockMvc mvc;

    @MockBean
    BookingService bookingService;

    @MockBean
	private CustomerService customerService;
	
	@MockBean
	private EstablishmentService establishmentService;
	
	@MockBean
	private RoomService roomService;
	
	@Before
	public void setUp() throws Exception {
		factory = new BookingTestDataFactory();
	}
	
	@Test
	public void shouldFindRoomBookings() throws Exception {
	    given(bookingService.getBookingsForRoom(ROOM_ID))
        .willReturn(factory.getRoomBookings(2, ROOM_ID));

	    mvc.perform(get("/bookings/room/" + ROOM_ID).accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.data", hasSize(2)))
	            .andExpect(jsonPath("$.data[0].bookingId").value(BOOKING_ID))
	    		.andExpect(jsonPath("$.data[1].bookingId").value(BOOKING_ID + 1));
	}

	@Test
	public void shouldFindCustomerBookings() throws Exception {
	    given(bookingService.getBookingsForCustomer(CUSTOMER_ID))
        .willReturn(factory.getCustomerBookings(1, CUSTOMER_ID));

	    mvc.perform(get("/bookings/customer/" + CUSTOMER_ID).accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.data", hasSize(1)))
	            .andExpect(jsonPath("$.data[0].bookingId").value(BOOKING_ID));
	}

	@Test
	public void shouldFindCustomerAvailability() throws Exception {
		given(bookingService.getAvailabilityForRoom(ROOM_ID, LocalDate.now(), LocalDate.now().plusDays(2)))
				.willReturn(Lists.newArrayList(factory.getAvailability(3)));

		mvc.perform(get("/bookings/room/" + ROOM_ID + "/availability/from/" + LocalDate.now() + "/to/"
				+ LocalDate.now().plusDays(2)).accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(3)))
				.andExpect(jsonPath("$.data[0]").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.data[1]").value(LocalDate.now().plusDays(1).toString()))
				.andExpect(jsonPath("$.data[2]").value(LocalDate.now().plusDays(2).toString()));
	}

    @Test
    public void shouldAddBooking() throws Exception {
        given(bookingService.addBooking(any())).willReturn(BOOKING_ID);
        given(roomService.getRoom(ROOM_ID)).willReturn(factory.getRoom(ROOM_ID));
        given(customerService.getCustomer(CUSTOMER_ID)).willReturn(factory.getCustomer(CUSTOMER_ID));
        given(establishmentService.getEstablishment(ESTABLISHMENT_ID)).willReturn(factory.getEstablishment());

        mvc.perform(post("/bookings")
            .content(factory.getBookingRequest())
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
        )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data").value(BOOKING_ID))
	    	.andExpect(jsonPath("$.status").value("SUCCESS"))	
	    	.andExpect(jsonPath("$.message", IsNull.nullValue()));
    }	
	
    @Test
	public void shouldFailWhenNoRoomBookings() throws Exception {
	    given(bookingService.getBookingsForRoom(ROOM_ID)).willReturn(new ArrayList<Booking>());

	    mvc.perform(get("/bookings/room/" + ROOM_ID).accept(APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.data", IsNull.nullValue()))
	    	.andExpect(jsonPath("$.status").value("FAIL"))	
	    	.andExpect(jsonPath("$.message").value("No resource found"));
	}


    @Test
	public void shouldFailWhenNoCustomerBookings() throws Exception {
	    given(bookingService.getBookingsForRoom(CUSTOMER_ID)).willReturn(new ArrayList<Booking>());

	    mvc.perform(get("/bookings/customer/" + CUSTOMER_ID).accept(APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.data", IsNull.nullValue()))
	    	.andExpect(jsonPath("$.status").value("FAIL"))	
	    	.andExpect(jsonPath("$.message").value("No resource found"));
	}
}
