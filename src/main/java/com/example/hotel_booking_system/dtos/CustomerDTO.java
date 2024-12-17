package com.example.hotel_booking_system.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;

public class CustomerDTO {
    private Long customerId;

    private String customerName;
    
    @Email(message = "Email should be valid")
    private String customerEmail;

    private List<BookingDTO> bookings;

    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, String customerName, @Email(message = "Email should be valid") String customerEmail,
            List<BookingDTO> bookings) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.bookings = bookings;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String email) {
        this.customerEmail = email;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }
}
