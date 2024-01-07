package com.example.customer.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    private int rate;

    private String content;

    private String name;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;
}
