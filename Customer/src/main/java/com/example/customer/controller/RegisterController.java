package com.example.customer.controller;

import com.example.customer.domain.Customer;
import com.example.customer.responseBody.BodyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {

    @GetMapping()
    public String register()
    {
        return "register";
    }

    @GetMapping("confirm")
    public String confirm_Register()
    {
        return "test";
    }

    @GetMapping("OTP")
    public  String otp()
    {
        return "OTP";
    }

}
