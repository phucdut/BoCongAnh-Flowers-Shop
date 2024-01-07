package com.example.admin.Service.Impl;

import com.example.admin.Converter.UserConverter;
import com.example.admin.Domain.Review;
import com.example.admin.Domain.User;
import com.example.admin.Repository.ReviewRepository;
import com.example.admin.Converter.ReviewConverter;
import com.example.admin.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll().stream().map(ReviewConverter::toModel).toList();
    }
    @Override
    public Review getReviewById(Long userId) {
        return ReviewConverter.toModel(reviewRepository.findById(userId).orElseThrow());
    }
    @Override
    public void detailReview(Review review){

    }
}
