package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.hotel_booking_system.dtos.RoomDTO;
import com.example.hotel_booking_system.models.Hotel;
import com.example.hotel_booking_system.models.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(target = "hotel.id", source = "hotelId")
    Room toEntity(RoomDTO dto);

    @Mapping(target = "hotelId", source = "hotel.id", defaultValue = "0")
    RoomDTO toDTO(Room entity);

    default int mapHotelToHotelId(Room room) {
        return room.getHotel() != null ? room.getHotel().getId() : 0;
    }

    default Room mapHotelIdToHotel(int hotelId) {
        Room room = new Room();
        if (hotelId != 0) {
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);
            room.setHotel(hotel);
        }
        return room;
    }
    
}
