package com.example.admin.Service;



import com.example.admin.Domain.OrderDetail;
import com.example.admin.Domain.OrderDetailHistory;
import com.example.admin.Domain.Product;
import com.example.admin.Entity.OrderDetailEntity;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailHistory> findByOrder(Long orderId);
    List<Product> getTop4Product();
}
