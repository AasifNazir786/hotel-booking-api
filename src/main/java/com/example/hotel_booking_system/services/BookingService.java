package com.example.hotel_booking_system.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.models.Booking;
import com.example.hotel_booking_system.repository.BookingRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCustomer(int customerId) {

        return bookingRepository.findByCustomerId(customerId);
    }

    public List<Booking> getBookingsByRoom(int roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    public List<Booking> getBookingsInDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByArrivalDateBetween(startDate, endDate);
    }

    public void deleteBooking(int bookingId) {
        if(!bookingRepository.existsById(bookingId)){
            throw new EntityNotFoundException("Booking not exists with id: "+bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }
}
