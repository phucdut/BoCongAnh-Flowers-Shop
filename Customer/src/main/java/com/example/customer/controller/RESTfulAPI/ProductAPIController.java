package com.example.customer.controller.RESTfulAPI;

import com.example.customer.domain.Product;
import com.example.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductAPIController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProductNonSale());
    }
    @GetMapping("flash-sale")
    public ResponseEntity<List<Product>> getAllProductFlashSale() {
        productService.updateExpiredFlashSale();
        return ResponseEntity.ok(productService.getAllProductSale());
    }
    @GetMapping("best-seller")
    public ResponseEntity<List<Product>> getAllProductBestSeller() {
        return ResponseEntity.ok(productService.getProductBestSeller());
    }
    @GetMapping("related/{id}")
    public ResponseEntity<List<Product>> getAllProductRelated(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getAllProductRelated(id));
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("search-category/{id}")
    public ResponseEntity<List<Product>> getAllProductByCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getAllProductByCategory(id));
    }


}
