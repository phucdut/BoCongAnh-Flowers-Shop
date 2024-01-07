package com.example.customer.repository;

import com.example.customer.entity.CustomerEntity;
import com.example.customer.entity.ProductEntity;
import com.example.customer.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByProductEntity(ProductEntity productEntity);

    List<ReviewEntity> findAllByCustomerEntity(CustomerEntity customerEntity);
}
