package com.example.customer.converter;

import com.example.customer.domain.FlashSale;
import com.example.customer.entity.FlashSaleEntity;

public class FlashSaleConverter {
    public static FlashSale toModel(FlashSaleEntity entity) {
        FlashSale flashSale = new FlashSale();
        flashSale.setId(entity.getId());
        flashSale.setProductId(entity.getProductEntity().getId());
        flashSale.setSale(entity.getSale());
        flashSale.setPriceSale(entity.getPriceSale());
        flashSale.setStartDate(entity.getStartDate());
        flashSale.setEndDate(entity.getEndDate());
        flashSale.setExpired(entity.isExpired());
        return flashSale;
    }
}
