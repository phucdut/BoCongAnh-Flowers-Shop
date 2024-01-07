package com.example.admin.Controller.Admin;

import com.example.admin.Domain.*;
import com.example.admin.Service.ImportGoodsDetailService;
import com.example.admin.Service.ImportGoodsService;
import com.example.admin.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/importGoodsDetail")
public class ImportGoodsDetailController {
    @Autowired
    private ImportGoodsService importGoodsService;

    @Autowired
    private ImportGoodsDetailService importGoodsDetailService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/{importGoodsId}")
    public String showImportGoodsDetails(@PathVariable Long importGoodsId, Model model) {
        List<ImportGoodsDetail> importGoodsDetails = importGoodsDetailService.getImportGoodsDetailByImportGoodsId(importGoodsId);
        List<Item> items = itemService.getAllItem();
        List<ImportGood> importGoods = importGoodsService.getAllImportGoods();
        model.addAttribute("importGoods", importGoods);
        model.addAttribute("items", items);
        model.addAttribute("importGoodsDetails", importGoodsDetails);
        return "Admin/ImportGoodsDetailAdmin";
    }
}
