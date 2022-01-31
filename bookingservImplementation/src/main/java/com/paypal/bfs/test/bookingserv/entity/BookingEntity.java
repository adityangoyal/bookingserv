package com.paypal.bfs.test.bookingserv.entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String idempotencyId;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Temporal(TemporalType.DATE)
    private Date checkinDate;

    @Temporal(TemporalType.DATE)
    private Date checkoutDate;

    private Double totalPrice;
    private Double deposit;

    @Embedded
    private AddressDTO address;


    public BookingEntity() {}


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdempotencyId() {
        return idempotencyId;
    }

    public void setIdempotencyId(String idempotencyId) {
        this.idempotencyId = idempotencyId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Date getCheckinDate() {
        return checkinDate;
    }


    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }


    public Date getCheckoutDate() {
        return checkoutDate;
    }


    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }


    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Double getDeposit() {
        return deposit;
    }


    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }


    public AddressDTO getAddress() {
        return address;
    }


    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
