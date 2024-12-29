package com.example.hotel_booking_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.dtos.BookingDTO;
import com.example.hotel_booking_system.generics_repository.GenericBookingRepo;
import com.example.hotel_booking_system.jpa_repository.BookingRepository;
import com.example.hotel_booking_system.jpa_repository.CustomerRepository;
import com.example.hotel_booking_system.jpa_repository.RoomRepository;
import com.example.hotel_booking_system.mappers.BookingMapper;
import com.example.hotel_booking_system.models.Booking;
import com.example.hotel_booking_system.models.Customer;
import com.example.hotel_booking_system.models.Room;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookingService implements GenericBookingRepo{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    /*
     * for saving a booking into the database
     */
    @Override
    public BookingDTO create(BookingDTO dto){

        Booking booking = bookingMapper.toEntity(dto);
        if(dto.getRoomId() != null)
            booking.setRoom(validateAndRetrieveRoom(dto.getRoomId()));
        
        if(dto.getCustomerId() != null)
            booking.setCustomer(validateAndRetrieveCustomer(dto.getCustomerId()));

        Booking savedBooking = bookingRepository.save(booking);
        return mapBookingToBookingDTO(savedBooking);
    }

    /*
     * for retrieving a page of bookings from the database
     */
    public List<BookingDTO> getPageOfBookings(){

        return bookingRepository.findAll()
                .stream()
                .map(this::mapBookingToBookingDTO)
                .toList();
    }

    /*
     * retrieving a booking by bookingId
     */
    @Override
    public BookingDTO getById(Long id){
        if(id == null){
            throw new IllegalArgumentException("enter valid id");
        }
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking Not found with id: "+id));

        return mapBookingToBookingDTO(booking);
    }

    /*
     * updating a booking by bookingId
     */
    @Override
    public BookingDTO update(Long id, BookingDTO dto){
        
        if(id == null){
            throw new IllegalArgumentException("enter valid id");
        }

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking Not found with id: "+id));

        booking.setArrivalDate(dto.getArrivalDate());
        booking.setDepartureDate(dto.getDepartureDate());
        booking.setDaysStayed(dto.getDaysStayed());
        if(dto.getRoomId() != null)
            booking.setRoom(validateAndRetrieveRoom(dto.getRoomId()));
        
        if(dto.getCustomerId() != null)
            booking.setCustomer(validateAndRetrieveCustomer(dto.getCustomerId()));

        Booking updatedBooking = bookingRepository.save(booking);
        return mapBookingToBookingDTO(updatedBooking);
    }

    /*
     * delete booking by bookingId
     */
    @Override
    public void deleteById(Long id){
        
        if(bookingRepository.existsById(id)){
            bookingRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("booking not exists with id: "+id);
    }
    
    /*
     * Helper Functions
     */
    private Room validateAndRetrieveRoom(Long id){
        
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: "+id));
    }

    private Customer validateAndRetrieveCustomer(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: "+id));
    }
    
    private BookingDTO mapBookingToBookingDTO(Booking booking){

        BookingDTO dto = bookingMapper.toDTO(booking);
        if(booking.getCustomer().getCustomerId() != null)
            dto.setCustomerId(booking.getCustomer().getCustomerId());

        if(booking.getRoom().getId() != null)
            dto.setRoomId(booking.getRoom().getId());

        return dto;
    }
}