package com.example.hotel_booking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hotel_booking_system.enums.RoomType;
import com.example.hotel_booking_system.models.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{

    @Query("select r from Room r join r.hotel h where h.id = :id")
    List<Room> findAllByHotelId(@Param("id") int id);

//    List<Room> findByType(RoomType type);
//
//    List<Room> findByIsAvailable(boolean isAvailable);

    @Query("SELECT r FROM Room r WHERE r.type = :type AND r.price > :minPrice")
    List<Room> findRoomsByTypeAndPriceGreaterThan(@Param("type") RoomType type, @Param("minPrice") double minPrice);

}
