package com.example.customer.controller;


import com.example.customer.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String loginAdmin(Model model) {
        model.addAttribute("user", new UserEntity());
        return "Login";
    }

}
