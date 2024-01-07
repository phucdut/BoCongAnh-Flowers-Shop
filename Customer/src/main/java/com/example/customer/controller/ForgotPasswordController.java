package com.example.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ForgotPasswordController {
    @GetMapping("forgot-password")
    public String forgotPassword()
    {
        return "forgotPassword";
    }
}
