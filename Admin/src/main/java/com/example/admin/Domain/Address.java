package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Long id;

    private Long customerId;

    private String city;

    private String district;

    private String ward;

    private String street;

    private String nameCustomer;

    private String phoneNumber;
}