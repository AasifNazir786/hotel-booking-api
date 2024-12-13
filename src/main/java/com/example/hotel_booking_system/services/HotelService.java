package com.example.hotel_booking_system.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.dtos.HotelDTO;
import com.example.hotel_booking_system.dtos.RoomDTO;
import com.example.hotel_booking_system.mappers.HotelMapper;
import com.example.hotel_booking_system.mappers.RoomMapper;
import com.example.hotel_booking_system.models.Hotel;
import com.example.hotel_booking_system.models.Room;
import com.example.hotel_booking_system.repository.HotelRepository;
import com.example.hotel_booking_system.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired RoomRepository roomRepository;

    @Transactional
    public Hotel createHotel(HotelDTO hotelDTO){

        Hotel hotel = HotelMapper.INSTANCE.toEntity(hotelDTO);

        if(hotelDTO.getRooms() != null){

            hotel.setRooms(saveAndMapRoomsToEntities(hotelDTO.getRooms(), hotel));
        }
        return hotelRepository.save(hotel);
    }

    public List<HotelDTO> getAllHotels(){

        return hotelRepository.findAll()
        .stream()
        .map(HotelMapper.INSTANCE::toDTO)
        .collect(Collectors.toList());
    }

    @Transactional
    public HotelDTO getById(int id){
        
        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("hotel with id: "+id+" not found"));

        return HotelMapper.INSTANCE.toDTO(hotel);
    }

    @Transactional
    public HotelDTO updateHotelById(int id, HotelDTO dto){

        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(()->new EntityNotFoundException("hotel not found with id: "+id));
        
        hotel.setName(dto.getHotelName());
        hotel.setAddress(dto.getHotelAddress());
        hotel.setRating(dto.getHotelRating());
        
        List<Room> rooms = new ArrayList<>();
        if(dto.getRooms() != null){
            rooms = saveAndMapRoomsToEntities(dto.getRooms(), hotel);
        }
        hotel.setRooms(rooms);
        
        return HotelMapper.INSTANCE.toDTO(hotel);
    }


    // HELPER FUNCTIONS
    private List<Room> saveAndMapRoomsToEntities(List<RoomDTO> roomDTOs, Hotel hotel) {
        List<Room> existingRooms = roomRepository.findAllByHotelId(hotel.getId());
        Map<Integer, Room> existingRoomMap = mapExistingRoomsById(existingRooms);
    
        // Create or update rooms
        List<Room> rooms = mapAndSaveRooms(roomDTOs, hotel, existingRoomMap);
    
        // Remove orphaned rooms
        removeOrphanedRooms(existingRooms, roomDTOs);
    
        return rooms;
    }
    
    private Map<Integer, Room> mapExistingRoomsById(List<Room> existingRooms) {
        return existingRooms
                .stream()
                .collect(Collectors.toMap(Room::getId, room -> room));
    }
    
    private List<Room> mapAndSaveRooms(List<RoomDTO> roomDTOs, Hotel hotel, Map<Integer, Room> existingRoomMap) {
        return roomDTOs.stream()
                .map(dto -> {
                    Room room;
                    if (dto.getId() != 0 && existingRoomMap.containsKey(dto.getId())) {
                        room = updateRoom(existingRoomMap.get(dto.getId()), dto);
                    } else {
                        room = createRoom(dto, hotel);
                    }
                    return room;
                })
                .collect(Collectors.toList());
    }
    
    private Room updateRoom(Room existingRoom, RoomDTO dto) {
        existingRoom.setAvailable(dto.isAvailable());
        existingRoom.setPricePerDay(dto.getPricePerDay());
        existingRoom.setType(dto.getType());
        return existingRoom;
    }
    
    private Room createRoom(RoomDTO dto, Hotel hotel) {
        Room room = RoomMapper.INSTANCE.toEntity(dto);
        room.setHotel(hotel);
        return room;
    }
    
    private void removeOrphanedRooms(List<Room> existingRooms, List<RoomDTO> roomDTOs) {

        List<Integer> incomingRoomIds = roomDTOs
                .stream()
                .map(RoomDTO::getId)
                .filter(id -> id != null)
                .collect(Collectors.toList());
    
        List<Room> orphanedRooms = existingRooms
                .stream()
                .filter(room -> !incomingRoomIds.contains(room.getId()))
                .collect(Collectors.toList());
    
        roomRepository.deleteAll(orphanedRooms);
    }
}