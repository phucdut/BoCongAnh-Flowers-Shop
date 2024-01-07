package com.example.admin.Repository;

import com.example.admin.Entity.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RevenueRepository extends JpaRepository<RevenueEntity, Long> {
    RevenueEntity findByDate(LocalDate date);
}
