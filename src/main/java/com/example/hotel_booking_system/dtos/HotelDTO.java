package com.example.hotel_booking_system.dtos;

import java.util.List;

import com.example.hotel_booking_system.enums.HotelRating;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

public class HotelDTO {
    
    private Integer hotelId;

    @NotNull(message = "Hotel name can't be null")
    private String hotelName;

    private String hotelAddress;

    @Enumerated(EnumType.STRING)
    private HotelRating hotelRating;

    @OneToMany(mappedBy = "hotel")
    List<RoomDTO> rooms;

    public HotelDTO(Integer hotelId, @NotNull(message = "Hotel name can't be null") String hotelName,
            String hotelAddress, HotelRating hotelRating, List<RoomDTO> rooms) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelRating = hotelRating;
        this.rooms = rooms;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
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

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    
}
