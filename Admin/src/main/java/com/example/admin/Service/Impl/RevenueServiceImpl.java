package com.example.admin.Service.Impl;

import com.example.admin.Converter.RevenueConverter;
import com.example.admin.Domain.Revenue;
import com.example.admin.Entity.FlashSaleEntity;
import com.example.admin.Entity.OrderHistoryEntity;
import com.example.admin.Entity.RevenueEntity;
import com.example.admin.Repository.OrderHistoryRepository;
import com.example.admin.Repository.RevenueRepository;
import com.example.admin.Service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public List<Revenue> getAllRevenue() {
        // Lấy danh sách RevenueEntity từ repository
        List<RevenueEntity> revenueEntities = revenueRepository.findAll();

        // Lặp qua từng RevenueEntity và cập nhật totalRevenue
        for (RevenueEntity revenueEntity : revenueEntities) {
            LocalDate date = revenueEntity.getDate();
            updateTotalRevenue(date); // Cập nhật totalRevenue cho ngày hiện tại
        }

        // Chuyển đổi danh sách RevenueEntity sang danh sách Revenue và trả về
        return revenueEntities.stream()
                .map(RevenueConverter::toModel)
                .toList();
    }
    public void updateTotalRevenue(LocalDate date) {
        List<OrderHistoryEntity> orderHistories = orderHistoryRepository.findByDate(date);

        Long totalRevenue = orderHistories.stream()
                .mapToLong(OrderHistoryEntity::getTotalPrice)
                .sum();

        // Kiểm tra xem đã có bản ghi trong bảng RevenueEntity cho ngày đó chưa
        RevenueEntity existingRevenue = revenueRepository.findByDate(date);

        if (existingRevenue != null) {
            // Nếu đã tồn tại, cập nhật totalRevenue
            existingRevenue.setTotalRevenue(totalRevenue);
            revenueRepository.save(existingRevenue);
        } else {
            // Nếu chưa tồn tại, tạo mới bản ghi trong bảng RevenueEntity
            RevenueEntity newRevenue = new RevenueEntity();
            newRevenue.setDate(date);
            newRevenue.setTotalRevenue(totalRevenue);
            revenueRepository.save(newRevenue);
        }
    }
}
