package com.example.hotel_booking_system.models;

import java.util.List;

import com.example.hotel_booking_system.enums.HotelRating;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "hotels")
public class Hotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    @NotNull(message = "Hotel name can't be null")
    private String hotelName;
    private String hotelAddress;

    @Enumerated(EnumType.STRING)
    private HotelRating hotelRating;

    @OneToMany(mappedBy = "hotel")
    List<Room> rooms;

    public Hotel() {}

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public @NotNull(message = "Hotel name can't be null") String getHotelName() {
        return hotelName;
    }

    public void setHotelName(@NotNull(message = "Hotel name can't be null") String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public HotelRating getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(HotelRating hotelRating) {
        this.hotelRating = hotelRating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel(int hotelId, String hotelName, HotelRating hotelRating, String hotelAddress, List<Room> rooms) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelRating = hotelRating;
        this.hotelAddress = hotelAddress;
        this.rooms = rooms;
    }
}


