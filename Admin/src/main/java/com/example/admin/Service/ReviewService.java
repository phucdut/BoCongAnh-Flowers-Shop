package com.example.admin.Service;

import com.example.admin.Domain.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview();

    Review getReviewById(Long id);

    void detailReview(Review review);
}
