package com.example.hotel_booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hotel_booking_system.enums.HotelRating;
import com.example.hotel_booking_system.models.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Integer>{
    
    Hotel findByName(String name);

    List<Hotel> findByRating(HotelRating rating);

    @Query("SELECT h FROM Hotel h JOIN h.rooms r WHERE r.price BETWEEN :minPrice AND :maxPrice")
    List<Hotel> findHotelsWithRoomsInPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}
