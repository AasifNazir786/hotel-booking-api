package com.example.hotel_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel_booking_system.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    Customer findByEmail(String email);

    Customer findByName(String name);
}
