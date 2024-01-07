package com.example.admin.Repository;

import com.example.admin.Entity.OrderEntity;
import com.example.admin.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where o.orderDateTime > ?1 and o.orderDateTime <= ?2")
    public List<OrderEntity> findByDate(LocalDateTime from, LocalDateTime to);

}
