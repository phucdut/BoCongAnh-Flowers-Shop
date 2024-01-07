package com.example.customer.controller.RESTfulAPI;


import com.example.customer.domain.Review;
import com.example.customer.requestBody.ReviewRequest;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.service.OrderHistoryService;
import com.example.customer.service.ProductService;
import com.example.customer.service.ReviewService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/review")
public class ReviewAPIController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CustomerValidate customerValidate;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("{productId}")
    public ResponseEntity<List<Review>> getReviewByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getAllReviewByProduct(productId));
    }

    @PostMapping("add-review/{order_id}")
    public ResponseEntity<BodyResponse> addReview(@RequestBody ReviewRequest reviews, @PathVariable Long order_id) {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        reviewService.addReview(reviews, name);
        orderHistoryService.setReviewed(order_id);
        productService.updateRating(reviews.getReviews());
        BodyResponse response = new BodyResponse();
        response.setSuccess(true);
        response.setMessage("success");
        return ResponseEntity.ok(response);
    }
}
