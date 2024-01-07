package com.example.customer.service.Impl;

import com.example.customer.converter.ProductConverter;
import com.example.customer.domain.Product;
import com.example.customer.domain.Review;
import com.example.customer.entity.*;
import com.example.customer.repository.FlashSaleRepository;
import com.example.customer.repository.OrderDetailHistoryRepository;
import com.example.customer.repository.ProductRepository;
import com.example.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Autowired
    private OrderDetailHistoryRepository orderDetailHistoryRepository;


    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll().stream().map(ProductConverter::toModel).toList();
    }

    @Override
    public List<Product> getAllProductByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(ProductConverter::toModel).toList();
    }

    @Override
    public Product getProductById(Long id) {
        return ProductConverter.toModel(productRepository.findById(id).orElseThrow());
    }

    @Override
    public List<Product> getAllProductSale() {
        return productRepository.findByFlashSaleEntityExpiredFalse().stream().map(ProductConverter::toModelSale).toList();
    }

    @Override
    public void updateExpiredFlashSale() {
        List<FlashSaleEntity> flashSaleEntities = flashSaleRepository.findAllByExpiredFalse();
        for (FlashSaleEntity flashSaleEntity: flashSaleEntities) {
            if (flashSaleEntity.getEndDate().isBefore(LocalDate.now())) {
                flashSaleEntity.setExpired(true);
                flashSaleRepository.save(flashSaleEntity);
            }
        }
    }

    @Override
    public List<Product> getProductBestSeller() {
        List<Object[]> products = orderDetailHistoryRepository.findTop10SellingProductsLimited();
        List<ProductEntity> productList = productRepository.findAll();
        List<Product> topProducts = new ArrayList<>();
        for (Object[] productData : products) {
            Long productId = (Long) productData[0];
            for (ProductEntity prod : productList) {
                if (prod.getId() == productId) {
                    topProducts.add(ProductConverter.toModel(prod));
                }
            }
        }
        return topProducts;
    }

    @Override
    public List<Product> getAllProductNonSale() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductEntity> productNonSales = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            if (entity.getFlashSaleEntity() == null || entity.getFlashSaleEntity().isExpired()) {
                productNonSales.add(entity);
            }
        }
        return productNonSales.stream().map(ProductConverter::toModel).toList();
    }

    @Override
    public List<Product> getAllProductRelated(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        List<CategoryEntity> categoryEntities = productEntity.getCategoryEntities();
        List<ProductEntity> productRelated = new ArrayList<>();
        // Kiểm tra xem danh sách danh mục có rỗng hay không
        if (categoryEntities != null && !categoryEntities.isEmpty()) {
            // Duyệt qua danh sách danh mục để kiểm tra tên danh mục
            for (CategoryEntity categoryEntity : categoryEntities) {
                if ("Gift".equals(categoryEntity.getName())) {
                    // Nếu tìm thấy danh mục có tên là "Gift", thực hiện xử lý tương ứng
                    // Ví dụ: Trả về danh sách sản phẩm liên quan
                    List<ProductEntity> productEntities = productRepository.findAll();
                    Collections.shuffle(productEntities);
                    productRelated = productEntities.subList(0, Math.min(6, productEntities.size()));
                    return productRelated.stream().map(ProductConverter::toModel).toList();
                } else {
                    List<ProductEntity> productEntities = productRepository.findAllByCategoryName("Gift");
                    Collections.shuffle(productEntities);
                    productRelated = productEntities.subList(0, Math.min(6, productEntities.size()));
                    return productRelated.stream().map(ProductConverter::toModel).toList();
                }
            }
        }
        return null;
    }

    @Override
    public void updateRating(List<Review> reviews) {
        for (Review review : reviews) {
            ProductEntity productEntity = productRepository.findById(review.getProductId()).orElseThrow();
            productEntity.setOverall_rating(calculateOverallRating(productEntity.getReviewEntities()));
            productRepository.save(productEntity);
        }

    }

    private double calculateOverallRating(List<ReviewEntity> reviews) {
        double overallRating = 0.0;
        if (reviews == null || reviews.isEmpty()) {
            return overallRating;
        }

        double totalRating = 0.0;
        int numberOfReviews = reviews.size();

        for (ReviewEntity review : reviews) {
            totalRating += review.getRate();
        }

        overallRating = totalRating / numberOfReviews;
        return roundToTwoDecimalPlaces(overallRating);
    }

    private double roundToTwoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }
}
