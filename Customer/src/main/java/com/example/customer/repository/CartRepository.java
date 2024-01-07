package com.example.customer.repository;

import com.example.customer.entity.CartEntity;
import com.example.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findByCustomerEntity(CustomerEntity customerEntity);
}
