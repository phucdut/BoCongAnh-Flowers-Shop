package com.example.admin.Controller.Admin;

import com.example.admin.Domain.ImportGood;
import com.example.admin.Domain.OrderHistory;
import com.example.admin.Domain.User;
import com.example.admin.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.admin.Service.ImportGoodsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("admin/importGood")
public class ImportGoodsController {
    @Autowired
    private ImportGoodsService importGoodsService;

    @Autowired
    private ItemService itemService;
    @GetMapping()
    public String listImportGoods(Model model) {
        model.addAttribute("importGoods", importGoodsService.getAllImportGoods());
        model.addAttribute("items", itemService.getAllItem());
        return "Admin/ImportGoodsAdmin";
    }
    @GetMapping("/search")
    public String searchOrderHistory (@RequestParam("startTime") LocalDateTime startTime,
                                      @RequestParam("endTime") LocalDateTime endTime,Model model){
        System.out.println("Thời điểm bắt đầu 1: " + startTime);
        System.out.println("Thời điểm kết thúc: " + endTime);
        List<ImportGood> importGoods = importGoodsService.getImportGoodByTime(startTime,endTime);
        model.addAttribute("importGoods", importGoods );
        model.addAttribute("items",itemService.getAllItem());
        return "Admin/ImportGoodsSearchAdmin";
    }
}
