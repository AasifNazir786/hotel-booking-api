package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.hotel_booking_system.dtos.BookingDTO;
import com.example.hotel_booking_system.models.Booking;

@Mapper(componentModel="spring", uses={RoomMapper.class, CustomerMapper.class})
public interface BookingMapper {
    
    @Mapping(source="roomId", target="room.id")
    @Mapping(source="customerId", target="customer.customerId")
    Booking toEntity(BookingDTO dto);

    @Mapping(target="roomId", source="room.id")
    @Mapping(target="customerId", source="customer.customerId")
    BookingDTO toDTO(Booking booking);
}
