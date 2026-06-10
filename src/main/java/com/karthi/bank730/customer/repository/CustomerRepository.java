package com.karthi.bank730.customer.repository;

import com.karthi.bank730.customer.entiry.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmailAndAadhaarNumber(String email, String aadhaarNumber);
}
