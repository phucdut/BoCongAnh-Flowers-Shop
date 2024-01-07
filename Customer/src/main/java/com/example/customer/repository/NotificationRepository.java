package com.example.customer.repository;

import com.example.customer.entity.CustomerEntity;
import com.example.customer.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByCustomerEntity(CustomerEntity customerEntity);
}
