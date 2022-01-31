package com.paypal.bfs.test.bookingserv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.AddressDTO;
import com.paypal.bfs.test.bookingserv.entity.BookingEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelMapper {


    public static Booking getBooking(BookingEntity bookingEntity) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Booking booking = new Booking();

        booking.setId(bookingEntity.getId());
        booking.setFirstName(bookingEntity.getFirstName());
        booking.setLastName(bookingEntity.getLastName());
        booking.setBirthDate(dateFormat.format(bookingEntity.getBirthDate()));
        booking.setCheckinDate(dateFormat.format(bookingEntity.getCheckinDate()));
        booking.setCheckoutDate(dateFormat.format(bookingEntity.getCheckoutDate()));
        booking.setDeposit(bookingEntity.getDeposit());
        booking.setTotalPrice(bookingEntity.getTotalPrice());
        booking.setAddress(getAddress(bookingEntity.getAddress()));
        return booking;
    }

    public static BookingEntity getBookingEntity(Booking booking) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setFirstName(booking.getFirstName());
        bookingEntity.setLastName(booking.getLastName());
        bookingEntity.setDeposit(booking.getDeposit());
        bookingEntity.setTotalPrice(booking.getTotalPrice());
        bookingEntity.setAddress(getAddressDTO(booking.getAddress()));
        try {
            bookingEntity.setBirthDate(dateFormat.parse(booking.getBirthDate()));
            bookingEntity.setCheckinDate(dateFormat.parse(booking.getCheckinDate()));
            bookingEntity.setCheckoutDate(dateFormat.parse(booking.getCheckoutDate()));
        } catch (ParseException e) {
            log.warn("Error parsing date", e);
        }
        bookingEntity.setIdempotencyId(Idempotency.getIdempotencyId(booking));
        return bookingEntity;
    }

    private static Address getAddress(AddressDTO addressDto) {

        Address address = new Address();
        address.setLine1(addressDto.getLine1());
        address.setLine2(addressDto.getLine2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setZipCode(addressDto.getZipCode());
        return address;
    }

    private static AddressDTO getAddressDTO(Address address) {

        AddressDTO addressDto = new AddressDTO();
        addressDto.setLine1(address.getLine1());
        addressDto.setLine2(address.getLine2());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        addressDto.setZipCode(address.getZipCode());
        return addressDto;
    }
}