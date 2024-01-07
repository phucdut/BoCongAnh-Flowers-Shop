package com.example.customer.service.Impl;

import com.example.customer.converter.CategoryConverter;
import com.example.customer.domain.Category;
import com.example.customer.repository.CategoryRepository;
import com.example.customer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll().stream().map(CategoryConverter::toModel).toList();
    }
}
