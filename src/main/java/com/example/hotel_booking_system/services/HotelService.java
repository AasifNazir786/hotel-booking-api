package com.example.hotel_booking_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.enums.HotelRating;
import com.example.hotel_booking_system.models.Hotel;
import com.example.hotel_booking_system.repository.HotelRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Page<Hotel> getHotelsByRating(HotelRating rating, Pageable pageable) {
        return hotelRepository.findHotelsByRatingWithPagination(rating, pageable);
    }

    public List<Hotel> getHotelsWithRoomsInPriceRange(double minPrice, double maxPrice) {
        return hotelRepository.findHotelsWithRoomsInPriceRange(minPrice, maxPrice);
    }

    public Hotel getHotelByName(String name) {
        return hotelRepository.findByName(name);
    }

    public void deleteHotel(int hotelId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new EntityNotFoundException("Hotel not exists with Id: "+ hotelId);
        }
        hotelRepository.deleteById(hotelId);
    }
}
