package com.example.admin.Converter;

import com.example.admin.Domain.Product;
import com.example.admin.Domain.ProductDTO;
import com.example.admin.Entity.ProductEntity;

import java.util.stream.Collectors;

public class ProductConverter {
    public static Product toModel(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setOriginalPrice(productEntity.getOriginal_price());
        product.setPrice(productEntity.getPrice());
        product.setDescription(productEntity.getDescription());
        product.setDetails(productEntity.getDetails());
        product.setDelivery(productEntity.getDelivery());
        product.setSub_info(productEntity.getSub_info());
        product.setOverall_rating(productEntity.getOverall_rating());
        product.setDiscount(productEntity.getDiscount());
        product.setImage1(productEntity.getImage1());
        product.setImage2(productEntity.getImage2());
        product.setImage3(productEntity.getImage3());
        product.setImage4(productEntity.getImage4());
        product.setImage5(productEntity.getImage5());
        product.setDeleted(productEntity.isDeleted());

//        product.setReviews(productEntity.getReviewEntities().stream().map(ReviewConverter::toModel).collect(Collectors.toList()));
        product.setCategories(productEntity.getCategoryEntities().stream().map(CategoryConverter::toModel).collect(Collectors.toList()));

        return product;
    }

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setOriginal_price(product.getOriginalPrice());
        entity.setPrice(product.getPrice());
        entity.setDescription(product.getDescription());
        entity.setDetails(product.getDetails());
        entity.setDelivery(product.getDelivery());
        entity.setSub_info(product.getSub_info());
        entity.setOverall_rating(product.getOverall_rating());
        entity.setDiscount(product.getDiscount());
        entity.setImage1(product.getImage1());
        entity.setImage2(product.getImage2());
        entity.setImage3(product.getImage3());
        entity.setImage4(product.getImage4());
        entity.setImage5(product.getImage5());
        entity.setDeleted(false);

//        entity.setReviewEntities(ReviewConverter.toEntityList(product.getReviews()));
//        entity.setCategoryEntities(product.getCategories().stream().map(CategoryConverter::toEntity).collect(Collectors.toList()));


        return entity;
    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setOriginalPrice(product.getOriginalPrice());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setDetails(product.getDetails());
        dto.setDelivery(product.getDelivery());
        dto.setSubInfo(product.getSub_info());
        dto.setOverall_rating(product.getOverall_rating());
        dto.setDiscount(product.getDiscount());
        return dto;
    }

}
