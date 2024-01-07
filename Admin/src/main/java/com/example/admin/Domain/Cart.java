package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Cart {
    private Long id;

    private List<CartItem> cartItems;

    private Customer customer;
}
