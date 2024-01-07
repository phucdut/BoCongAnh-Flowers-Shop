package com.example.admin.Repository;

import com.example.admin.Domain.Product;
import com.example.admin.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//    Page<ProductEntity> findAllProducts(PageRequest pageRequest);
    Optional<ProductEntity> findByName(String name);
}
