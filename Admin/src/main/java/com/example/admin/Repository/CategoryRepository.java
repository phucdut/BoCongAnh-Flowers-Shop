package com.example.admin.Repository;

import com.example.admin.Entity.CategoryEntity;
import com.example.admin.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
//    List<CategoryEntity> findAllByProductEntities(ProductEntity productEntity);
    List<CategoryEntity> findAllByIdIn(List<Long> ids);
}
