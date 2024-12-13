package com.example.hotel_booking_system.models;

import com.example.hotel_booking_system.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Min(value = 0, message = "price must be a positive number")
    private double pricePerDay;
    
    @NotNull(message = "Availability cannot be null")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonBackReference
    private Hotel hotel;
    
    public Room() {}

    public Room(int id, RoomType type, double pricePerDay, boolean isAvailable) {
        this.id = id;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
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

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @NotNull(message = "Availability cannot be null")
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(@NotNull(message = "Availability cannot be null") boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
