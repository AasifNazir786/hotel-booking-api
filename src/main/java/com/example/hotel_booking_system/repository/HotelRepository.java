package com.example.hotel_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_booking_system.models.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{
    
//    Hotel findByName(String name);

//    @Query("SELECT h FROM Hotel h JOIN h.rooms r WHERE r.price BETWEEN :minPrice AND :maxPrice")
//    List<Hotel> findHotelsWithRoomsInPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
//
//    @Query("SELECT h FROM Hotel h WHERE h.rating = :rating")
//    Page<Hotel> findByRatingWithPagination(@Param("rating") HotelRating rating, Pageable pageable);
//
//    @Query("SELECT h FROM Hotel h WHERE h.address = :address")
//    Page<Hotel> findByAddress(@Param("address") String address, Pageable pageable);


}
