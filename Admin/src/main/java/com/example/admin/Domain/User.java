package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class User {
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String image;

    private String phone;

    private String address;

    private LocalDate birthday;

    private String salary;

    private String role;

    private Boolean deleted;

    private List<Order> orders;
}
