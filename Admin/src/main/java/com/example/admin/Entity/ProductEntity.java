package com.example.admin.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long original_price;

    private Long price;

    private String description;

    private String details;

    private String delivery;

    private String sub_info;

    private double overall_rating;

    private Long discount;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String image5;

    private boolean deleted;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviewEntities;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "category_product_mapping",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categoryEntities;

    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private FlashSaleEntity flashSaleEntity;
}
