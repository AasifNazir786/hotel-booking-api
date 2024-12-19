package com.example.hotel_booking_system.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private Long bookingId;  // Reference to Booking by ID

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, BigDecimal amount, LocalDateTime paymentDate, Long bookingId) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.bookingId = bookingId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}

