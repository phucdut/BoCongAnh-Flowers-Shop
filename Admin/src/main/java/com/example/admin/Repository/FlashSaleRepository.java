package com.example.admin.Repository;

import com.example.admin.Entity.FlashSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashSaleRepository extends JpaRepository<FlashSaleEntity, Long> {
    List<FlashSaleEntity> findAllByExpiredFalse();
    List<FlashSaleEntity> findAllByExpiredTrue();
}
