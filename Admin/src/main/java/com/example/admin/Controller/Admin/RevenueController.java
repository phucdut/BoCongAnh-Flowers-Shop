package com.example.admin.Controller.Admin;

import com.example.admin.Service.OrderService;
import com.example.admin.Service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/revenue")
public class RevenueController {
    @Autowired
    RevenueService revenueService;

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String listRevenue(Model model) {
        model.addAttribute("revenues", revenueService.getAllRevenue());
        return "Admin/RevenueAdmin";
    }
}
