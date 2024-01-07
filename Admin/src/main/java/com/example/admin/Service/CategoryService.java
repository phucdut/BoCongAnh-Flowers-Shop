package com.example.admin.Service;

import com.example.admin.Domain.Category;
import com.example.admin.Domain.CategoryDTO;
import com.example.admin.Domain.CategoryData;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

//    List<Category> getAllCategoriesByProductId(Long productId);
    boolean addCategory(CategoryDTO category);
    Category getCategoryById(Long categoryId);

    void updateCategory(CategoryDTO category);
    void deleteCategoryById(Long categoryId);
    void restoreCategoryById(Long categoryId);
    List<CategoryData> getAllCategoryData();
}
