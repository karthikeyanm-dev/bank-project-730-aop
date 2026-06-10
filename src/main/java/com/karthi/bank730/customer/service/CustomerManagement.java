package com.karthi.bank730.customer.service;

import com.karthi.bank730.customer.dto.CustomerCreationRequest;
import com.karthi.bank730.customer.dto.CustomerResponse;
import com.karthi.bank730.customer.entiry.Customer;
import com.karthi.bank730.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class CustomerManagement {
    private final CustomerRepository customerRepository;

    public CustomerResponse createNewCustomer(CustomerCreationRequest customerCreationRequest){

//        Check weather the user already registered
        if(customerRepository.existsByEmailAndAadhaarNumber(
                customerCreationRequest.email(),customerCreationRequest.pan()
        )){
            throw  new RuntimeException("Customer Already Exists");
        }
        Customer customer = new Customer();
        customer.setCustomerId("test1"); // AutoGenerate
        customer.setFullName(customerCreationRequest.fullName());
        customer.setEmail(customerCreationRequest.email());
        customer.setMobileNumber( customerCreationRequest.mobileNumber());
        customer.setAddress( customerCreationRequest.address());
        customer.setAge(customer.getAge());
        customer.setMinor(
                customerCreationRequest.age() < 18 ? true : false
        );
        customer.setPAN(customerCreationRequest.pan());
        customer.setAadhaarNumber( customerCreationRequest.aadhaarNumber());

        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse(
                savedCustomer.getCustomerId(),
                savedCustomer.getFullName()
        );
        return response;
    }

}
