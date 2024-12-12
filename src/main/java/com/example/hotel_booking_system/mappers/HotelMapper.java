package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.hotel_booking_system.dtos.HotelDTO;
import com.example.hotel_booking_system.models.Hotel;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    Hotel toEntity(HotelDTO dto);

    HotelDTO toDTO(Hotel entity);
    
}
