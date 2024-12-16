package com.example.hotel_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_booking_system.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
}
