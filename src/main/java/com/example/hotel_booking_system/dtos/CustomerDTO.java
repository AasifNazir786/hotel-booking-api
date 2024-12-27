package com.example.hotel_booking_system.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;

public class CustomerDTO extends BasicCustomerDTO{

    private List<BookingDTO> bookings;

    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, String customerName, @Email(message = "Email should be valid") String customerEmail,
            List<BookingDTO> bookings) {
        super(customerId, customerName, customerEmail);
        this.bookings = bookings;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }
}
