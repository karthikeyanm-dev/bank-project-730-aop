package com.karthi.bank730.customer.controller;

import com.karthi.bank730.customer.dto.CustomerCreationRequest;
import com.karthi.bank730.customer.dto.CustomerResponse;
import com.karthi.bank730.customer.entiry.Customer;
import com.karthi.bank730.customer.service.CustomerManagement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerManagement customerManagement;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerCreationRequest customerCreationRequest){
        return new ResponseEntity<>(
                customerManagement.createNewCustomer(customerCreationRequest), HttpStatus.CREATED
        );
    }


    // Create a customer ID Unique
    // Aadhaar and pan validation - optional try
    // Other Controllers
}
