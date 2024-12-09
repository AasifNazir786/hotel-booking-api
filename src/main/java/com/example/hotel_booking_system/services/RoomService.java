package com.example.hotel_booking_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.enums.RoomType;
import com.example.hotel_booking_system.models.Room;
import com.example.hotel_booking_system.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getRoomsByTypeAndPriceGreaterThan(RoomType type, double minPrice) {
        return roomRepository.findRoomsByTypeAndPriceGreaterThan(type, minPrice);
    }

    public Room getRoomById(int roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new EntityNotFoundException("Room not found with id"+roomId));
    }

    public List<Room> getByAvailable(boolean available){
        return roomRepository.findByAvailable(available);
    }

    public List<Room> getByType(RoomType type){
        return roomRepository.findByType(type);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(int roomId) {
        if(!roomRepository.existsById(roomId)){
            throw new EntityNotFoundException("Room not exists with id"+roomId);
        }
        roomRepository.deleteById(roomId);
    }
    
}
