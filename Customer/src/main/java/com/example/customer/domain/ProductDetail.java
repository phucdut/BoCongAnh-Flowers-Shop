package com.example.customer.domain;


import com.example.customer.entity.ItemEntity;
import com.example.customer.entity.ProductEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetail {
    private Long id;

    private Long itemId;

    private int quantity;

    private Long productId;
}
