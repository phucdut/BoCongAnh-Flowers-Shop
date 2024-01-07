package com.example.admin.Converter;

import com.example.admin.Domain.FlashSale;
import com.example.admin.Entity.FlashSaleEntity;

public class FlashSaleConverter {
    public static FlashSale toModel(FlashSaleEntity entity) {
        FlashSale flashSale = new FlashSale();
        flashSale.setId(entity.getId());
        flashSale.setProduct(ProductConverter.toModel(entity.getProductEntity()));
        flashSale.setSale(entity.getSale());
        flashSale.setPriceSale(entity.getPriceSale());
        flashSale.setStartDate(entity.getStartDate());
        flashSale.setEndDate(entity.getEndDate());
        flashSale.setExpired(entity.isExpired());
        return flashSale;
    }
    public static FlashSaleEntity toEntity(FlashSale flashSale){
        FlashSaleEntity flashSaleEntity = new FlashSaleEntity();
        flashSaleEntity.setId(flashSale.getId());
        flashSaleEntity.setSale(flashSale.getSale());
        flashSaleEntity.setPriceSale(flashSale.getPriceSale());
        flashSaleEntity.setEndDate(flashSale.getEndDate());
        flashSaleEntity.setExpired(flashSale.isExpired());
        flashSaleEntity.setStartDate(flashSale.getStartDate());

        flashSaleEntity.setProductEntity(ProductConverter.toEntity(flashSale.getProduct()));

        return flashSaleEntity;
    }
}
