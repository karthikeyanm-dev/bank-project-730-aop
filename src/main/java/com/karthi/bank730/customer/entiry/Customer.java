package com.karthi.bank730.customer.entiry;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String address;
    private Integer age;
    private boolean isMinor;

    @Column(unique = true)
    private String PAN;

    @Column(unique = true)
    private String aadhaarNumber;
}
