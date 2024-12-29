package com.example.hotel_booking_system.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel_booking_system.dtos.BookingDTO;
import com.example.hotel_booking_system.services.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingDTO createBooking(@RequestBody @Valid BookingDTO bookingDTO){

        return bookingService.create(bookingDTO);
    }

    @GetMapping
    public List<BookingDTO> getPageOfBookings(Pageable pageable){

        return bookingService.getPageOfBookings(pageable);
    }

    @GetMapping("/{id}")
    public BookingDTO getBookingById(Long id){

        return bookingService.getById(id);
    }

    @PutMapping("/update/{id}")
    public BookingDTO updateBooking(@RequestBody @Valid BookingDTO bookingDTO, @PathVariable Long id){

        return bookingService.update(id, bookingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id){

        bookingService.deleteById(id);
    }
}
