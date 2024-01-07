package com.example.admin.Controller.Admin;

import com.example.admin.Domain.FlashSale;
import com.example.admin.Domain.Voucher;
import com.example.admin.Service.FlashSaleService;
import com.example.admin.Service.ProductService;
import com.example.admin.Service.VoucherService;
import com.example.admin.enums.VoucherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/flash-sale")
public class FlashSaleController {
    @Autowired
    private FlashSaleService flashSaleService;

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String listFlashSale(Model model) {
        model.addAttribute("flashSales", flashSaleService.getAllFlashSale());
        return "Admin/FlashSaleAdmin";
    }

    @GetMapping("add")
    public String showAddFlashSale(Model model) {
        model.addAttribute("flashSale", new FlashSale());
        model.addAttribute("product", productService.getAllProduct());
        return "Admin/AddFlashSaleAdmin";
    }
    @PostMapping("add")
    public String addFlashSale(@ModelAttribute FlashSale flashSale, Model model) {
        if (flashSaleService.checkFlashSale(flashSale)) {
            System.out.println("Vào đây: ");
            flashSaleService.addFlashSale(flashSale);
            return "redirect:/admin/flash-sale";
        }
        System.out.println("Vào đây 1: ");
        model.addAttribute("error", "Flash sale dành cho Product này đã tồn tại!!! Mời bạn nhập Product khác");
        return "Admin/AddFlashSaleAdmin";
    }
    @GetMapping("edit/{id}")
    public String showEditFlashSale(@PathVariable String id, Model model) {
        Long flashSaleId = Long.parseLong(id);
        model.addAttribute("flashSale", flashSaleService.getFlashSaleById(flashSaleId));
        return "Admin/EditFlashSaleAdmin";
    }
    @PostMapping("edit")
    public String editFlashSale(@ModelAttribute FlashSale flashSale) {
        flashSaleService.updateFlashSale(flashSale);
        return "redirect:/admin/flash-sale";
    }
    @GetMapping("delete/{id}")
    public String deleteFlashSale(@PathVariable String id) {
        Long flashSaleId = Long.parseLong(id);
        flashSaleService.deleteFlashSaleById(flashSaleId);
        return "redirect:/admin/flash-sale";
    }

    @GetMapping("restore")
    public String listFlashSaleRestore(Model model) {
        model.addAttribute("flashSales", flashSaleService.getAllFlashSale());
        return "Admin/RestoreFlashSaleAdmin";
    }
}
