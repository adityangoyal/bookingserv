package com.paypal.bfs.test.bookingserv.impl;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.bookingserv.BookingServApplication;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BookingServApplication.class)
@AutoConfigureMockMvc
public class BookingResourceImplTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createNewBooking() throws Exception {

        Booking booking = new Booking();
        booking.setFirstName("First");
        booking.setLastName("Last");
        booking.setBirthDate("2022/01/01");
        booking.setCheckinDate("2022/01/01");
        booking.setCheckoutDate("2022/01/01");
        booking.setTotalPrice(1D);
        booking.setDeposit(1D);

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Contry");
        address.setZipCode("111");

        booking.setAddress(address);

        String postBody = new ObjectMapper().writeValueAsString(booking);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/booking").content(postBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void createDuplicateBooking() throws Exception {

        Booking booking = new Booking();
        booking.setFirstName("First");
        booking.setLastName("Last");
        booking.setBirthDate("2022/01/01");
        booking.setCheckinDate("2022/01/01");
        booking.setCheckoutDate("2022/01/01");
        booking.setTotalPrice(1D);
        booking.setDeposit(1D);

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Contry");
        address.setZipCode("111");

        booking.setAddress(address);

        String postBody = new ObjectMapper().writeValueAsString(booking);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/booking").content(postBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewBookingWithBadRequest() throws Exception {

        Booking booking = new Booking();
        booking.setFirstName("First");
        booking.setLastName("Last");
        booking.setBirthDate("2022/01/01");
        booking.setCheckinDate("2022/01/02");
        booking.setCheckoutDate("2022/01/02");



        String postBody = new ObjectMapper().writeValueAsString(booking);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/booking").content(postBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void retrieveBookings() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/booking")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}