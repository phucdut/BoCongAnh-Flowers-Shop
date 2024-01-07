package com.example.admin.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "flash_sale")
@Getter
@Setter
public class FlashSaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sale;

    private Long priceSale;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean expired;

    @OneToOne
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;
}
