package com.example.hotel_booking_system.generics;

public interface BaseRepository<T> {

    T create(T dto);
    T getById(Long id);
    T update(Long id, T dto);
    void deleteById(Long id);
}
