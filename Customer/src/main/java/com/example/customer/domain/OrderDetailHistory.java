package com.example.customer.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailHistory {
    private Long id;
    private Long orderHistory_id;
    private Long productId;
    private String nameProduct;
    private Long priceProduct;
    private String image;
    private int quantity;
}
