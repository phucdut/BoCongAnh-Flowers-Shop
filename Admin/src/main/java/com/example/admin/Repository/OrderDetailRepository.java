package com.example.admin.Repository;

import com.example.admin.Entity.OrderDetailEntity;
import com.example.admin.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

    @Query("select o from OrderDetailEntity o where o.orderEntity.id = ?1")
    public List<OrderDetailEntity> findByOrder(Long orderId);

}
