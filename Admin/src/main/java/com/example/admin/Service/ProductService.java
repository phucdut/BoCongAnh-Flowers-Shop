package com.example.admin.Service;

import com.example.admin.Domain.Product;
import com.example.admin.Domain.ProductDTO;
import com.example.admin.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

//    void addProduct(Product product);

    Product getProductById(Long productId);

    void updateProduct(ProductDTO product);

    void deleteProductById(Long productId);

    void restoreProductById(Long productId);

    Product getById(Long id);

    ProductEntity createProduct(ProductDTO productDto);

    void setCategories(ProductEntity productEntity, ProductDTO productDto);

    boolean checkNameProduct(String name);

//    Page<ProductEntity> getAllProductsPaged(int page, int size);
}
