package com.example.customer.repository;

import com.example.customer.entity.FlashSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSaleEntity, Long> {

    List<FlashSaleEntity> findAllByExpiredFalse();
}
