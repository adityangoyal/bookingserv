package com.paypal.bfs.test.bookingserv.util;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

public class Idempotency {

    public static String getIdempotencyId(Booking booking) {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(booking.getFirstName())
        .append("_")
        .append(booking.getLastName())
        .append("_")
        .append(booking.getBirthDate())
        .append("_")
        .append(booking.getCheckinDate())
        .append("_")
        .append(booking.getAddress().getZipCode());
        
        return sb.toString();
    }
}