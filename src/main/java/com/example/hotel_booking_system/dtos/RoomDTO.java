package com.example.hotel_booking_system.dtos;

import com.example.hotel_booking_system.enums.RoomType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RoomDTO {
    private Long id;

    private RoomType type;

    @Min(value = 0, message = "price must be a positive number")
    private double pricePerDay;

    @NotNull(message = "Availability cannot be null")
    private boolean isAvailable;

    private Long hotelId;

    public RoomDTO(Long id, RoomType type, double pricePerDay, boolean isAvailable, Long hotelId) {
        this.id = id;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
        this.hotelId = hotelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Min(value = 0, message = "price must be a positive number")
    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(@Min(value = 0, message = "price must be a positive number") double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @NotNull(message = "Availability cannot be null")
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(@NotNull(message = "Availability cannot be null") boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
