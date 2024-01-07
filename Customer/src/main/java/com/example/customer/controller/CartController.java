package com.example.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart")
public class CartController {
    @GetMapping String viewCart()
    {
        return "cart";
    }

    @GetMapping("checkout")
    public String checkout()
    {
        return "checkout";
    }
}
