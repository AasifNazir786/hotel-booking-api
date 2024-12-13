package com.example.hotel_booking_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hotel_booking_system.models.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{

    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.id = :id")
    List<Room> findAllByHotelId(@Param("id") int id);


    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.id = :hotelId AND r.isAvailable = true")
    Optional<List<Room>> findAvailableByHotelId(@Param("hotelId") int hotelId);

    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.id = :id")
    List<Room> findAllByHotelId(@Param("id") int id, Sort sort);
}
