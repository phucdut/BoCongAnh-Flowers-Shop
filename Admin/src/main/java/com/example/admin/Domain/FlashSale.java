package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FlashSale {
    private Long id;

    private Long sale;

    private Long priceSale;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean expired;

    private Product product;
}