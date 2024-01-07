package com.example.customer.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Review {
    private Long id;
    private Long productId;
    private int rate;
    private String content;
    private String name;
    private LocalDate date;
    private Customer customer;
}
