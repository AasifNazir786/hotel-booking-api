package com.example.hotel_booking_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotel_booking_system.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.id = :id")
    List<Room> findAllByHotelId(@Param("id") Long id);

    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.id = :hotelId AND r.isAvailable = true")
    Optional<List<Room>> findAvailableByHotelId(@Param("hotelId") Long hotelId);

    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.id = :id")
    List<Room> findAllByHotelId(@Param("id") Long id, Sort sort);
}
