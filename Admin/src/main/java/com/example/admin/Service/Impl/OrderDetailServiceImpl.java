package com.example.admin.Service.Impl;


import com.example.admin.Converter.OrderDetailConverter;
import com.example.admin.Converter.ProductConverter;
import com.example.admin.Domain.OrderDetail;
import com.example.admin.Domain.OrderDetailHistory;
import com.example.admin.Domain.Product;
import com.example.admin.Entity.*;
import com.example.admin.Repository.*;
import com.example.admin.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailHistoryRepository orderDetailHistoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDetailHistory> findByOrder(Long orderId) {
        List<OrderDetailHistoryEntity> list = orderDetailHistoryRepository.findByOrder(orderId);
        List<OrderDetailHistory> result = new ArrayList<>();
        for(OrderDetailHistoryEntity o : list){
            result.add(OrderDetailConverter.toOrderDetailHistory(o));
        }
        return result;
    }
    @Override
    public List<Product> getTop4Product() {
        List<Object[]> products = orderDetailHistoryRepository.findTop4SellingProductsLimited();
        List<Product> productList = productRepository.findAll().stream().map(ProductConverter::toModel).toList();
        List<Product> topProducts = new ArrayList<>();
        for (Object[] productData : products) {
            Long productId = (Long) productData[0];

            for (Product prod:productList) {
                if (Objects.equals(prod.getId(), productId)) {
                    Product product = new Product();
                    product.setId(productId);
                    product.setName(prod.getName());
                    product.setPrice(prod.getPrice());
                    topProducts.add(product);
                }
            }

        }
        return topProducts;
    }
}
