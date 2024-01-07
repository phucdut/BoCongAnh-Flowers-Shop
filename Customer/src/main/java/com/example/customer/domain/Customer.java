package com.example.customer.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Customer {

    private Long id;

    private String username;

    private String password;

    private String avatar;

    private String phone;

    private String fullName;

    private List<Address> addresses;

    private String email;

    private boolean sex;

    private LocalDate birthday;

    private String token;

    private List<OrderHistory> orders;

    private List<Review> reviews;
}
