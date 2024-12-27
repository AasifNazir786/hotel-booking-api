package com.example.hotel_booking_system.dtos;

import jakarta.validation.constraints.Email;

public class BasicCustomerDTO {
    private Long customerId;

    private String customerName;
    
    @Email(message = "Email should be valid")
    private String customerEmail;

    public BasicCustomerDTO() {
    }

    public BasicCustomerDTO(Long customerId, String customerName,
            @Email(message = "Email should be valid") String customerEmail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
