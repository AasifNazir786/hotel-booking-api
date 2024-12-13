// package com.example.hotel_booking_system.services;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.hotel_booking_system.models.Payment;
// import com.example.hotel_booking_system.repository.PaymentRepository;

// @Service
// public class PaymentService {

//     @Autowired
//     private PaymentRepository paymentRepository;

//     public Payment savePayment(Payment payment) {
//         return paymentRepository.save(payment);
//     }

//     public List<Payment> getPaymentsByBookingId(int bookingId) {
//         return paymentRepository.findPaymentsByBookingId(bookingId);
//     }

//     public List<Payment> getPaymentsInTimePeriod(LocalDateTime startDate, LocalDateTime endDate) {
//         return paymentRepository.findByPaymentDateBetween(startDate, endDate);
//     }

//     public void deletePayment(int paymentId) {
//         paymentRepository.deleteById(paymentId);
//     }
// }
