package com.example.admin.Controller.Admin;

import com.example.admin.Service.ImportGoodsService;
import com.example.admin.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("admin/report")
public class ReportController {
    @Autowired
    private ImportGoodsService importGoodsService;

    @Autowired
    private OrderService orderService;


    @GetMapping()
    public String viewReport() {
        return "Admin/ReportAdmin";
    }
    @GetMapping("/search")
    public String viewReport (@RequestParam("startTime") LocalDateTime startTime,
                                      @RequestParam("endTime") LocalDateTime endTime,Model model){
        Long totalImportAmount = importGoodsService.getTotalImportAmountByTime(startTime, endTime);
        Long totalRevenue = orderService.getTotalRevenueByTime(startTime, endTime);
        Long profit = totalRevenue - totalImportAmount;

        model.addAttribute("totalImportAmount", totalImportAmount);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("profit", profit);

        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);

        return "Admin/PageReportAdmin";
    }

}
