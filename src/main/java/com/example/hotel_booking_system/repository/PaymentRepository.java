package com.example.hotel_booking_system.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hotel_booking_system.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

    List<Payment> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT p FROM Payment p WHERE p.booking.id = :bookingId")
    List<Payment> findPaymentsByBookingId(@Param("bookingId") int bookingId);

}
