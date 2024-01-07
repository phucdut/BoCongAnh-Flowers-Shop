package com.example.customer.service;

import com.example.customer.domain.Review;
import com.example.customer.requestBody.ReviewRequest;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewByProduct(Long productId);

    void addReview(ReviewRequest reviews, String name);

    List<Review> getAllReviewByCustomer(String name);
}
