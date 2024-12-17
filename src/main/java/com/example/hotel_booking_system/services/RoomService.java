package com.example.hotel_booking_system.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.dtos.RoomDTO;
import com.example.hotel_booking_system.mappers.RoomMapper;
import com.example.hotel_booking_system.models.Hotel;
import com.example.hotel_booking_system.models.Room;
import com.example.hotel_booking_system.repository.HotelRepository;
import com.example.hotel_booking_system.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomMapper roomMapper;


    @Transactional
    public RoomDTO createRoom(RoomDTO dto){

        Room room = roomMapper.toEntity(dto);

        Hotel hotel = validateAndRetrieveHotel(dto);
        room.setHotel(hotel);

        Room savedRoom = roomRepository.save(room);

        return roomMapper.toDTO(savedRoom);
    }

    public List<RoomDTO> getAllAvailableRoomsByHotelId(int hotelId){

        hotelRepository.findById(hotelId)
            .orElseThrow(() -> new EntityNotFoundException("Hotel not found with Id: " + hotelId));

        List<Room> rooms = roomRepository.findAvailableByHotelId(hotelId).orElse(List.of());
        
        return convertToDTOs(rooms);
    }

    public List<RoomDTO> getAllByHotelIdSorted(int hotelId){

        hotelRepository.findById(hotelId)
            .orElseThrow(() -> new EntityNotFoundException("Hotel not found with Id: " + hotelId));

        // sorts the result by type and pricePerDay in ascending order
        Sort sort = Sort.by(Sort.Order.asc("type"), Sort.Order.asc("pricePerDay"));
        List<Room> rooms = roomRepository.findAllByHotelId(hotelId, sort);

        if(rooms == null){
            throw new IllegalArgumentException("can't find any room for hotel with id: "+ hotelId);
        }

        return convertToDTOs(rooms);
    }

    public RoomDTO getRoomById(int id){
        
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("room not found with id: "+ id));

        return roomMapper.toDTO(room);
    }

    @Transactional
    public RoomDTO updateOrCreateRoomDTO(int id, RoomDTO dto) {
    
        Room room = roomRepository.findById(id).orElse(null);
    
        if (room == null) {

            Hotel hotel = validateAndRetrieveHotel(dto);
            room = roomMapper.toEntity(dto);
            room.setHotel(hotel);

        } else {
            updateRoomFields(room, dto);
        }
    
        return roomMapper.toDTO(roomRepository.save(room));
    }
    



    // Helper functions
    private Hotel validateAndRetrieveHotel(RoomDTO dto){

        if(dto.getHotelId() != 0){

            return hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with Id: "+ dto.getHotelId()));

        }
        throw new IllegalArgumentException("Room must be associated with a valid Hotel.");
    }

    private List<RoomDTO> convertToDTOs(List<Room> rooms){

        return rooms
            .stream()
            .map(room -> {
                RoomDTO dto = roomMapper.toDTO(room);
                dto.setHotelId(room.getHotel().getHotelId());
                return dto;
            })
            .collect(Collectors.toList());
    }

    private void updateRoomFields(Room room, RoomDTO dto){
        room.setAvailable(dto.isAvailable());
        room.setPricePerDay(dto.getPricePerDay());
        room.setType(dto.getType());

        if (dto.getHotelId() != 0 && dto.getHotelId() != room.getHotel().getHotelId()) {
            Hotel hotel = validateAndRetrieveHotel(dto);
            room.setHotel(hotel);
        }
    }
}
