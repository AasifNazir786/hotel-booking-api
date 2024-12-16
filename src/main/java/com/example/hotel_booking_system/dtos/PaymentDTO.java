package com.example.hotel_booking_system.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

    private int id;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private int bookingId;  // Reference to Booking by ID

    public PaymentDTO() {
    }

    public PaymentDTO(int id, BigDecimal amount, LocalDateTime paymentDate, int bookingId) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.bookingId = bookingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}

