package com.example.hotel_booking_system.jpa_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hotel_booking_system.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

    @Query("select p from Payment p join p.booking b where b.id = :bookingId")
    List<Payment> findByBookingId(Long bookingId);

}
