package com.example.admin.Entity;

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
    @JoinColumn(name = "orderHistoryId")  // Đổi tên trường để tương thích với OrderEntity
    private OrderHistoryEntity orderHistoryEntity;

    private Long productId;

    private String nameProduct;

    private Long priceProduct;

    private String image;

    private int quantity;
}