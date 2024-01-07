package com.example.customer.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParsePosition;

@Controller
public class homeController {
    @GetMapping()
    public  String home()
    {
        return "home";
    }



}


