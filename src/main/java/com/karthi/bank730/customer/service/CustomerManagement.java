package com.karthi.bank730.customer.service;

import com.karthi.bank730.customer.dto.CustomerCreationRequest;
import com.karthi.bank730.customer.dto.CustomerResponse;
import com.karthi.bank730.customer.entiry.Customer;
import com.karthi.bank730.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.List;

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
        customer.setCustomerId(generateCustomerId()); // Check the method for auto generate logic
        customer.setFullName(customerCreationRequest.fullName());
        customer.setEmail(customerCreationRequest.email());
        customer.setMobileNumber( customerCreationRequest.mobileNumber());
        customer.setAddress( customerCreationRequest.address());
        customer.setAge(customerCreationRequest.age());
        customer.setMinor(
                customerCreationRequest.age() < 18 ? true : false
        );
        customer.setPAN(customerCreationRequest.pan());
        customer.setAadhaarNumber( customerCreationRequest.aadhaarNumber());

        Customer savedCustomer = customerRepository.save(customer);

        return mapToCustomerResponse(savedCustomer);
    }

    public String generateCustomerId(){
        int year = Year.now().getValue();

        String latestCustomerId = customerRepository.findTopByOrderByIdDesc().map(
//                customer -> customer.getCustomerId()
                Customer::getCustomerId
        ).orElse(null);

        long sequence = 1;

        if(latestCustomerId != null && latestCustomerId.startsWith("GP"+year)){
            sequence = Long.parseLong(latestCustomerId.substring(6)) +1;
        }
        return "GB"+year+sequence;
    }

    public CustomerResponse findCustomerById(String customerId) {
        return mapToCustomerResponse(
                customerRepository.findByCustomerId(customerId).orElseThrow(
                        () -> new RuntimeException("Customer Not Found")
                )
        );

    }

    public CustomerResponse mapToCustomerResponse(Customer customer){
        return  new CustomerResponse(
                customer.getCustomerId(),
                customer.getFullName()
        );
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(cust ->
                    mapToCustomerResponse(cust)
                ).toList();
    }
}
