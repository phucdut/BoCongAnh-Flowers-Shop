package com.example.admin.Controller.Staff;

import com.example.admin.Domain.Product;
import com.example.admin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("staff")
@CrossOrigin("*")
public class StaffProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/product")
    public String listCategory(Model model) {
        return "Staff/StaffProduct";
    }

    @GetMapping("/all-product")
    public ResponseEntity<?> findAll(){
        List<Product> result = productService.getAllProduct();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all-product-by-id")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        Product result = productService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
