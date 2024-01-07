package com.example.customer.service;

import com.example.customer.domain.Product;
import com.example.customer.domain.Review;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    List<Product> getAllProductByCategory(Long categoryId);

    Product getProductById(Long id);

    List<Product> getAllProductSale();

    void updateExpiredFlashSale();

    List<Product> getProductBestSeller();

    List<Product> getAllProductNonSale();

    List<Product> getAllProductRelated(Long id);

    void updateRating(List<Review> reviews);
}
