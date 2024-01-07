package com.example.admin.Converter;

import com.example.admin.Domain.Review;
import com.example.admin.Entity.ReviewEntity;

import java.util.List;
import java.util.stream.Collectors;


public class ReviewConverter {
    public static Review toModel(ReviewEntity reviewEntity) {
        Review review = new Review();
        review.setId(reviewEntity.getId());
        review.setName(reviewEntity.getName());
        review.setDate(reviewEntity.getDate());
        review.setRate(reviewEntity.getRate());
        review.setContent(reviewEntity.getContent());

        review.setProduct(ProductConverter.toModel(reviewEntity.getProductEntity()));
        review.setCustomer(CustomerConverter.toModel(reviewEntity.getCustomerEntity()));

        return review;
    }
}
