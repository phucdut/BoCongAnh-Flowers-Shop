package com.example.admin.Controller.Admin;

import com.example.admin.Converter.ProductConverter;
import com.example.admin.Domain.Category;
import com.example.admin.Domain.Product;
import com.example.admin.Domain.ProductDTO;
import com.example.admin.Service.CategoryService;
import com.example.admin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
//        model.addAttribute("categories", categoryService.getAllCategory());
        return "Admin/ProductAdmin";
    }
    @GetMapping("add")
    public String showProduct(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("product", new Product()); // Thay vì new ProductController()
        model.addAttribute("categories", categories);
        return "Admin/AddProductAdmin";
    }
    @GetMapping("edit/{id}")
    public String showEditProduct(@PathVariable String id, Model model) {
        List<Category> categories = categoryService.getAllCategory();
        Long productId = Long.parseLong(id);
        model.addAttribute("productDTO", ProductConverter.toProductDTO(productService.getProductById(productId)));
        model.addAttribute("categories", categories);
        return "Admin/EditProductAdmin";
    }
    @PostMapping("edit")
    public String editProduct(@ModelAttribute ProductDTO productDTO) {
        System.out.println("======");
        productService.updateProduct(productDTO);
        return "redirect:/admin/product";
    }
    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        Long productId = Long.parseLong(id);
        productService.deleteProductById(productId);
        return "redirect:/admin/product";
    }
    @GetMapping("restore")
    public String listProductRestore(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "Admin/RestoreProductAdmin";
    }
    @GetMapping("restore/{id}")
    public String restoreProduct(@PathVariable String id) {
        Long productId = Long.parseLong(id);
        productService.restoreProductById(productId);
        return "redirect:/admin/product/restore";
    }
}
