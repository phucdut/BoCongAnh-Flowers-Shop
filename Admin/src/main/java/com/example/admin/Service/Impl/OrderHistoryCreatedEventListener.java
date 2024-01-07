package com.example.admin.Service.Impl;

import com.example.admin.Entity.OrderHistoryEntity;
import com.example.admin.Service.RevenueService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderHistoryCreatedEventListener implements ApplicationListener<OrderHistoryCreatedEvent> {

    private final RevenueService revenueService;

    public OrderHistoryCreatedEventListener(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @Override
    public void onApplicationEvent(OrderHistoryCreatedEvent event) {
        // Xử lý sự kiện khi có một OrderHistoryEntity mới được tạo
        OrderHistoryEntity newOrderHistory = (OrderHistoryEntity) event.getSource();
        LocalDate orderDate = newOrderHistory.getOrderDateTime().toLocalDate();

        // Cập nhật hoặc thêm mới bản ghi trong bảng RevenueEntity
        revenueService.updateTotalRevenue(orderDate);
    }
}
