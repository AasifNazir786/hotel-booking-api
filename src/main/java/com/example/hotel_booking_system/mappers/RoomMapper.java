package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.hotel_booking_system.dtos.RoomDTO;
import com.example.hotel_booking_system.models.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(target = "hotel.hotelId", source = "hotelId")
    Room toEntity(RoomDTO dto);

    @Mapping(target = "hotelId", source = "hotel.hotelId", defaultValue = "0")
    RoomDTO toDTO(Room entity);
}
