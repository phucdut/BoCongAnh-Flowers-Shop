package com.example.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_detail_histories")
@Getter
@Setter
public class OrderDetailHistoryEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderHistoryEntity orderHistoryEntity;

    private Long productId;

    private String nameProduct;

    private Long priceProduct;

    private String image;

    private int quantity;
}
