package com.example.customer.repository;

import com.example.customer.entity.CartEntity;
import com.example.customer.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findAllByCartEntity(CartEntity cartEntity);

    void deleteAllByCartEntity(CartEntity cartEntity);
}
