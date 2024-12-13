package com.example.hotel_booking_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel_booking_system.dtos.HotelDTO;
import com.example.hotel_booking_system.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Create a new hotel
    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO createdHotel = hotelService.createHotel(hotelDTO);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    // Get all hotels
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    // Get a hotel by its ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable int id) {
        HotelDTO hotel = hotelService.getById(id);
        return ResponseEntity.ok(hotel);
    }

    // Update a hotel by its ID
    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotelById(@PathVariable int id,@RequestBody HotelDTO hotelDTO) {
        HotelDTO updatedHotel = hotelService.updateHotelById(id, hotelDTO);
        return ResponseEntity.ok(updatedHotel);
    }

}
