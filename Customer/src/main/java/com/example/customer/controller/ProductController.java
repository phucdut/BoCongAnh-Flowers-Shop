package com.example.customer.controller;

import com.example.customer.domain.Product;
import com.example.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("product")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String product()
    {
        return "product";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product_Detail";
    }
}
