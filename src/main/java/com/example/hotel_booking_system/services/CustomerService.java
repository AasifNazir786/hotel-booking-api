package com.example.hotel_booking_system.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.dtos.BasicCustomerDTO;
import com.example.hotel_booking_system.dtos.BookingDTO;
import com.example.hotel_booking_system.dtos.CustomerDTO;
import com.example.hotel_booking_system.generics_repository.GenericCustomerRepo;
import com.example.hotel_booking_system.jpa_repository.CustomerRepository;
import com.example.hotel_booking_system.jpa_repository.RoomRepository;
import com.example.hotel_booking_system.mappers.BookingMapper;
import com.example.hotel_booking_system.mappers.CustomerMapper;
import com.example.hotel_booking_system.models.Booking;
import com.example.hotel_booking_system.models.Customer;
import com.example.hotel_booking_system.models.Room;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerService implements GenericCustomerRepo{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private RoomRepository roomRepository;

    /*
     * creates a new customer...
     */
    @Transactional
    @Override
    public CustomerDTO create(CustomerDTO dto) {

        Customer customer = customerMapper.toEntity(dto);
        
        if(dto.getBookings() != null){

            customer.setBookings(convertBookingDtoToBookings(dto.getBookings(), customer));
        }
        
        return mapCustomerToCustomerDTO(customerRepository.save(customer));
    }
    
    @Override
    public CustomerDTO getById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    /*
     * method retrieves the customer without associated bookings
     */
    public BasicCustomerDTO getByLongId(Long id){

        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("customer not found wiith id: "+ id));
        
        return customerMapper.toBasicDTO(customer);
    }

    /*
     * this updtes an existing customer...
     */
    @Transactional
    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with Id: " + id));

        existingCustomer.setCustomerName(dto.getCustomerName());
        existingCustomer.setCustomerEmail(dto.getCustomerEmail());

        if (dto.getBookings() != null) {
            existingCustomer.setBookings(convertBookingDtoToBookings(dto.getBookings(), existingCustomer));
        }

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return mapCustomerToCustomerDTO(updatedCustomer);
    }

    /*
     * deletes a customer from a database...
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with Id: " + id);
        }
        customerRepository.deleteById(id);
    }
    
    /*
     * Helper Functions
     */

    // this method returning the List of Bookings with there Room and Customer
    private List<Booking> convertBookingDtoToBookings(List<BookingDTO> dtos, Customer customer){
        
        return dtos
            .stream()
            .map((dto) -> {
                Booking booking = new Booking();
                booking.setArrivalDate(dto.getArrivalDate());
                booking.setDepartureDate(dto.getDepartureDate());
                booking.setDaysStayed(getDaysStayed(dto.getArrivalDate(), dto.getDepartureDate()));
                if(dto.getRoomId() != null){
                    booking.setRoom(validateAndRetrieveRoom(dto.getRoomId()));
                }
                booking.setCustomer(customer);
                return booking;
            })
            .toList();
    }

    // this method validates and retrieves the Room from the database
    private Room validateAndRetrieveRoom(Long id){

        return roomRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Room not found with id: "+ id));
    }

    // this method maps the Customer to CustomerDTO
    private CustomerDTO mapCustomerToCustomerDTO(Customer savedCustomer) {

        CustomerDTO dto = customerMapper.toDTO(savedCustomer);

        if(savedCustomer.getBookings() != null){
            dto.setBookings(convertBookingsToBookingDTOs(savedCustomer.getBookings(), savedCustomer.getCustomerId()));
        }
        return dto;
    }

    // this method converts Bookings to BookingDTOs
    private List<BookingDTO> convertBookingsToBookingDTOs(List<Booking> bookings, Long id){

        return bookings.stream().map((booking) -> {
            BookingDTO dto = bookingMapper.toDTO(booking);
            dto.setCustomerId(id);
            if(booking.getRoom().getId() != null){
                dto.setRoomId(booking.getRoom().getId());
            }
            return dto;
        })
        .toList();
    }

    // gets the daysStayed
    private int getDaysStayed(LocalDate arrivalDate, LocalDate departureDate) {
        if (arrivalDate == null || departureDate == null) {
            throw new IllegalArgumentException("Both arrivalDate and departureDate must be set.");
        }
        if (!departureDate.isAfter(arrivalDate)) {
            throw new IllegalArgumentException("Departure date must be after arrival date.");
        }
        return (int)ChronoUnit.DAYS.between(arrivalDate, departureDate);
    }
}
