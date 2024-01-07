package com.example.customer.repository;

import com.example.customer.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT DISTINCT p FROM ProductEntity p JOIN p.categoryEntities c WHERE c.id = :categoryId")
    List<ProductEntity> findAllByCategoryId(@Param("categoryId") Long categoryId);

    List<ProductEntity> findByFlashSaleEntityExpiredFalse();

    @Query("SELECT DISTINCT p FROM ProductEntity p JOIN p.categoryEntities c WHERE c.name = :categoryName")
    List<ProductEntity> findAllByCategoryName(@Param("categoryName") String categoryName);
}

