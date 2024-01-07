package com.example.customer.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
