package com.example.hotel_booking_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.hotel_booking_system.dtos.BasicCustomerDTO;
import com.example.hotel_booking_system.dtos.CustomerDTO;
import com.example.hotel_booking_system.models.Customer;

@Mapper(componentModel="spring")
public interface CustomerMapper {
    
    @Mapping(target="bookings", ignore=true)
    Customer toEntity(CustomerDTO dto);

    @Mapping(target="bookings", ignore=true)
    CustomerDTO toDTO(Customer dto);

    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "customerEmail", source = "customerEmail")
    BasicCustomerDTO toBasicDTO(Customer customer);

}
