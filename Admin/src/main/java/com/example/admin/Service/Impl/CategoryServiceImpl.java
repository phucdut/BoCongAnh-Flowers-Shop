package com.example.admin.Service.Impl;


import com.example.admin.Converter.CategoryConverter;
import com.example.admin.Domain.Category;
import com.example.admin.Domain.CategoryDTO;
import com.example.admin.Domain.CategoryData;
import com.example.admin.Domain.StaffDTO;
import com.example.admin.Entity.CategoryEntity;
import com.example.admin.Entity.OrderDetailHistoryEntity;
import com.example.admin.Entity.UserEntity;
import com.example.admin.Repository.CategoryRepository;
import com.example.admin.Repository.OrderDetailHistoryRepository;
import com.example.admin.Repository.ProductRepository;
import com.example.admin.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailHistoryRepository orderDetailHistoryRepository;

    @Value("${imagePathProduct}")
    private String imagePath;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll().stream().map(CategoryConverter::toModel).toList();
    }
    @Override
    public boolean addCategory(CategoryDTO category) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findAll().stream()
                .filter(entity -> entity.getName().equals(category.getName()))
                .findFirst();
        CategoryEntity categoryEntity = new CategoryEntity();
        if (optionalCategory.isPresent() || category.getName() == null) {
            return false;
        }else {
            categoryEntity.setName(category.getName());
            forSaveImage(category, categoryEntity);
            categoryEntity.setDeleted(false);
            categoryEntity.setDetail(category.getDetail());
            categoryRepository.save(categoryEntity);
            return true;
        }

    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return CategoryConverter.toModel(categoryRepository.findById(categoryId).orElseThrow());
    }

    @Override
    public void updateCategory(CategoryDTO category) {
        CategoryEntity categoryEntity = categoryRepository.findById(category.getId()).orElseThrow();
        if(category.getImage() != null){
            forSaveImage(category, categoryEntity);
        }
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        categoryEntity.setDetail(category.getDetail());

        categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow();
        categoryEntity.setDeleted(true);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void restoreCategoryById(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow();
        categoryEntity.setDeleted(false);
        categoryRepository.save(categoryEntity);
    }
    @Override
    public List<CategoryData> getAllCategoryData() {
        List<CategoryData> categoryDataList = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<OrderDetailHistoryEntity> orderDetailHistoryEntities = orderDetailHistoryRepository.findAll();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryData categoryData = new CategoryData();
            categoryData.setQuantity(0);

            for (OrderDetailHistoryEntity productEntity : orderDetailHistoryEntities) {
                // Assuming that getCategories() returns a List<CategoryEntity> in ProductEntity
                List<CategoryEntity> productCategories = productRepository.findById(productEntity.getProductId())
                        .orElseThrow()
                        .getCategoryEntities();

                if (productCategories != null && !productCategories.isEmpty()) {
                    // Get the ID of the current category
                    Long currentCategoryId = categoryEntity.getId();

                    // Check if the current category's ID is in the list of product categories
                    if (productCategories.stream().anyMatch(category -> category.getId().equals(currentCategoryId))) {
                        categoryData.setCategoryName(categoryEntity.getName());
                        categoryData.setQuantity(categoryData.getQuantity() + productEntity.getQuantity());
                    }
                }
            }

            categoryDataList.add(categoryData);
        }

        return categoryDataList;
    }
    private void forSaveImage(CategoryDTO categoryDTO, CategoryEntity entity) {
        File file = new File(imagePath + categoryDTO.getName() + ".png");
        saveImageCategory(categoryDTO.getImage(), file);
        entity.setImage(categoryDTO.getName() + ".png");
    }

    private void saveImageCategory(MultipartFile image, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(image.getBytes());
            // Thêm dòng in để kiểm tra
            System.out.println("File has been saved successfully");
        } catch (IOException e) {
            e.printStackTrace(); // Thêm dòng này để in ra thông báo lỗi chi tiết
        }
    }
}
