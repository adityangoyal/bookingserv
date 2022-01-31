package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.model.Bookings;
import com.paypal.bfs.test.bookingserv.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;
import com.paypal.bfs.test.bookingserv.util.Idempotency;
import com.paypal.bfs.test.bookingserv.util.ModelMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookingResourceImpl implements BookingResource {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public ResponseEntity<Booking> create(Booking booking) {


        // maintaining idempotency
        Booking response = checkIfBookingExists(booking);
        if(response != null) {
            return new ResponseEntity<Booking>(response, HttpStatus.OK);
        }

        // create new booking
        try {
            BookingEntity entity = ModelMapper.getBookingEntity(booking);
            bookingRepository.save(entity);
            response = ModelMapper.getBooking(entity);
            return new ResponseEntity<Booking>(response, HttpStatus.CREATED);
        } catch(Exception e) {
            log.error("error during creating booking resource", e);
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Bookings> read() {

        try {
            List<BookingEntity> entitylist = (List<BookingEntity>) bookingRepository.findAll();
            if (entitylist == null || entitylist.isEmpty()) {
                return new ResponseEntity<Bookings>(HttpStatus.NOT_FOUND);
            }

            List<Booking> modelList = entitylist.stream().map(e -> ModelMapper.getBooking(e))
                    .collect(Collectors.toList());
            Bookings bookings = new Bookings();
            bookings.setBookingList(modelList);
            return new ResponseEntity<Bookings>(bookings, HttpStatus.OK);
        } catch(Exception e) {
            log.error("error during reading booking resource", e);
            return new ResponseEntity<Bookings>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Booking checkIfBookingExists(Booking booking) {

        String idempotencyId = Idempotency.getIdempotencyId(booking);
        List<BookingEntity> existingBookings = bookingRepository.findByIdempotencyId(idempotencyId);

        if(existingBookings != null && !existingBookings.isEmpty()) {

            Booking response = ModelMapper.getBooking(existingBookings.get(0));
            return response;
        }
        return null;
    }
}
