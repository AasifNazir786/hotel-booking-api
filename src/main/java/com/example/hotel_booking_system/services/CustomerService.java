package com.example.hotel_booking_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_booking_system.models.Customer;
import com.example.hotel_booking_system.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(int customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new EntityNotFoundException("customer not exists with id: "+customerId);
        }
        customerRepository.deleteById(customerId);
    }
}
