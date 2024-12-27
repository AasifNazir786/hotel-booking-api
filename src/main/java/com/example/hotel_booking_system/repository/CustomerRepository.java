package com.example.hotel_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_booking_system.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    // Customer findByEmail(String email);

    // Customer findByName(String name);
}
