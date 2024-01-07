package com.example.customer.converter;


import com.example.customer.domain.Category;
import com.example.customer.entity.CategoryEntity;

public class CategoryConverter {
    public static Category toModel(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setDeleted(categoryEntity.isDeleted());
        category.setImage(categoryEntity.getImage());
        category.setDetail(categoryEntity.getDetail());
//        category.setProducts(categoryEntity.getProductEntities().stream().map(ProductConverter::toModel).toList());
        return category;
    }

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        categoryEntity.setDeleted(false);
        return categoryEntity;
    }
}
