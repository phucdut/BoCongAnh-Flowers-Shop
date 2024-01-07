package com.example.customer.domain;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Address {
    private Long id;
    private String city;
    private String district;
    private String ward;
    private String street;
    private String nameCustomer;
    private String phoneNumber;
}
