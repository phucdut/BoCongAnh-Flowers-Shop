package com.example.customer.converter;

import com.example.customer.domain.Review;
import com.example.customer.entity.ReviewEntity;

public class ReviewConverter {
    public static Review toModel(ReviewEntity entity) {
        Review review = new Review();
        review.setId(entity.getId());
        review.setName(entity.getName());
        review.setDate(entity.getDate());
        review.setContent(entity.getContent());
        review.setRate(entity.getRate());
        review.setCustomer(CustomerConverter.toModel(entity.getCustomerEntity()));
        return review;
    }

}
