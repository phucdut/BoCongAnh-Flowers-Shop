package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Review {
    private Long id;

    private Product product;

    private int rate;

    private String content;

    private String name;

    private LocalDate date;

    private Customer customer;
}
