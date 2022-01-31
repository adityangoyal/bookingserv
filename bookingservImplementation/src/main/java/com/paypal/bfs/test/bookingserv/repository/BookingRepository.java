package com.paypal.bfs.test.bookingserv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.paypal.bfs.test.bookingserv.entity.BookingEntity;



public interface BookingRepository extends CrudRepository<BookingEntity, Integer>{

    public List<BookingEntity> findByIdempotencyId(String idempotencyId);
}