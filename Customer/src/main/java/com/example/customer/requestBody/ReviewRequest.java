package com.example.customer.requestBody;

import com.example.customer.domain.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewRequest {
    private List<Review> reviews;
}
