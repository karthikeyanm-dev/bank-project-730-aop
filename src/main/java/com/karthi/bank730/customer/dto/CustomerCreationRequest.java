package com.karthi.bank730.customer.dto;

public record CustomerCreationRequest(
        String fullName,
        String email,
        String mobileNumber,
        String address,
        Integer age,
        String aadhaarNumber,
        String pan

) {
}
