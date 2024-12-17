package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;

import com.example.hotel_booking_system.dtos.HotelDTO;
import com.example.hotel_booking_system.models.Hotel;

@Mapper(componentModel = "spring", uses={RoomMapper.class})
public interface HotelMapper {

    Hotel toEntity(HotelDTO dto);

    HotelDTO toDTO(Hotel entity);
    
}
