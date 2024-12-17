package com.example.hotel_booking_system.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hotel_booking_system.dtos.PaymentDTO;
import com.example.hotel_booking_system.mappers.PaymentMapper;
import com.example.hotel_booking_system.models.Booking;
import com.example.hotel_booking_system.models.Payment;
import com.example.hotel_booking_system.repository.BookingRepository;
import com.example.hotel_booking_system.repository.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private BookingRepository bookingRepository;

    /*
        Add a new payment for a booking
     */
    @Transactional
    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        
        Payment payment = paymentMapper.toEntity(paymentDTO);

        payment.setBooking(validateAndRetrieveBooking(paymentDTO.getBookingId()));
        payment.setPaymentDate(LocalDateTime.now());

        return mapEntityToDTO(paymentRepository.save(payment));
    }

    /*
        Get a payment by its ID
     */
    @Transactional(readOnly = true)
    public PaymentDTO getPaymentById(int paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + paymentId));

        return mapEntityToDTO(paymentRepository.save(payment));
    }

    /*
        Get all payments for a specific booking
     */
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByBookingId(int bookingId) {
        
        return paymentRepository.findByBookingId(bookingId)
                .stream()
                .map(this::mapEntityToDTO)
                .toList();
    }

    @Transactional(readOnly=true)
    public List<PaymentDTO> getAllPayments(){
        
        return paymentRepository.findAll()
            .stream()
            .map(this::mapEntityToDTO)
            .collect(Collectors.toList());
    }

    /*
        Helper functions
     */
    private Booking validateAndRetrieveBooking(int id){

        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("booking not found with id: "+id));
        
        return booking;
    }

    private PaymentDTO mapEntityToDTO(Payment payment){

        PaymentDTO dto = paymentMapper.toDTO(payment);

        dto.setBookingId(payment.getBooking().getId());

        return dto;
    }
}
