package com.example.hotel_booking_system.models;

import java.util.List;

import com.example.hotel_booking_system.enums.HotelRating;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private HotelRating rating;

    @OneToMany(mappedBy = "hotel")
    List<Room> rooms;

    public Hotel() {
    }

    public Hotel(int id, String name, String address, HotelRating rating, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotelRating getRating() {
        return rating;
    }

    public void setRating(HotelRating rating) {
        this.rating = rating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}


