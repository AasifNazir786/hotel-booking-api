package com.example.hotel_booking_system.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel_booking_system.dtos.RoomDTO;
import com.example.hotel_booking_system.services.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO dto){

        RoomDTO room = roomService.createRoom(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/hotels/{id}/available")
    public ResponseEntity<List<RoomDTO>> getAllAvailableRooms(@Valid @PathVariable("id") int hotelId){

        List<RoomDTO> dtos = roomService.getAllAvailableRoomsByHotelId(hotelId);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/hotels/{id}/sorted")
    public ResponseEntity<List<RoomDTO>> getAllRoomsSorted(@PathVariable("id") int hotelId){

        return ResponseEntity.ok(roomService.getAllByHotelIdSorted(hotelId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") int roomId){
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable int id, @Valid @RequestBody RoomDTO dto){

        RoomDTO roomDTO = roomService.updateOrCreateRoomDTO(id, dto);

        return ResponseEntity.ok(roomDTO);
    }

}