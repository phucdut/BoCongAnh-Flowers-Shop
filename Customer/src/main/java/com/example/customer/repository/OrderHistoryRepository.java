package com.example.customer.repository;

import com.example.customer.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {
    List<OrderHistoryEntity> findAllByCustomerId(Long customerId);

    OrderHistoryEntity findByIdAndCustomerId(Long orderId, Long customerId);
}
