package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.hotel_booking_system.dtos.RoomDTO;
import com.example.hotel_booking_system.models.Room;

@Mapper(componentModel = "spring", uses={Mapper.class})
public interface RoomMapper {
    
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(target = "hotel.hotelId", source = "hotelId")
    Room toEntity(RoomDTO dto);

    @Mapping(target = "hotelId", source = "hotel.hotelId", defaultValue = "0")
    @Mapping(target="isAvailable", source="available")
    RoomDTO toDTO(Room entity);
}
