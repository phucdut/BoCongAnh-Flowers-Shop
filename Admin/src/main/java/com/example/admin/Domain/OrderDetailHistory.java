package com.example.admin.Domain;

import com.example.admin.Entity.OrderHistoryEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailHistory {
    private Long id;

    private OrderHistory orderHistory;

    private Long productId;

    private String nameProduct;

    private Long priceProduct;

    private String image;

    private int quantity;
}
