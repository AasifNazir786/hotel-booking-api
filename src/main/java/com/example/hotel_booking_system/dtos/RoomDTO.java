package com.example.hotel_booking_system.dtos;

import com.example.hotel_booking_system.enums.RoomType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RoomDTO {
    private Integer id;

    private RoomType type;

    @Min(value = 0, message = "price must be a positive number")
    private double pricePerDay;

    @NotNull(message = "Availability cannot be null")
    private boolean isAvailable;

    private int hoteId;

    public RoomDTO(int id, RoomType type, double pricePerDay, boolean isAvailable, int hoteId) {
        this.id = id;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
        this.hoteId = hoteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setAvailable(@NotNull(message = "Availability cannot be null") boolean available) {
        isAvailable = available;
    }

    public int getHoteId() {
        return hoteId;
    }

    public void setHoteId(int hoteId) {
        this.hoteId = hoteId;
    }
}
