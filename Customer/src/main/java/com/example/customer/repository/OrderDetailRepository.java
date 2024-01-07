package com.example.customer.repository;

import com.example.customer.entity.OrderDetailEntity;
import com.example.customer.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findAllByOrderEntity(OrderEntity orderEntity);
}
