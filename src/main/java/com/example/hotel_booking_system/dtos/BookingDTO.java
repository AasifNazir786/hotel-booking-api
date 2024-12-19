package com.example.hotel_booking_system.dtos;

import java.time.LocalDate;

public class BookingDTO {

    private Long id;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int daysStayed;
    private Long roomId;
    private Long customerId;

    public BookingDTO() {
    }

    public BookingDTO(Long id, LocalDate arrivalDate, LocalDate departureDate, int daysStayed, Long roomId,
            Long customerId) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.daysStayed = daysStayed;
        this.roomId = roomId;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getDaysStayed() {
        return daysStayed;
    }

    public void setDaysStayed(int daysStayed) {
        this.daysStayed = daysStayed;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
