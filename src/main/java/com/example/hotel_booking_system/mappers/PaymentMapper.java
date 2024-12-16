package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.hotel_booking_system.dtos.PaymentDTO;
import com.example.hotel_booking_system.models.Payment;

@Mapper(componentModel="spring")
public interface PaymentMapper {
    
    PaymentMapper  INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source="bookingId", target="booking.id")
    Payment toEntity(PaymentDTO dto);

    @Mapping(target="bookingId", source="booking.id")
    PaymentDTO toDTO(Payment entity);
}
