package com.example.admin.Controller.Admin;

import com.example.admin.Converter.VoucherConverter;
import com.example.admin.Domain.Voucher;
import com.example.admin.Domain.VoucherDTO;
import com.example.admin.Service.VoucherService;
import com.example.admin.enums.VoucherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping()
    public String listVoucher(Model model) {
//        model.addAttribute("vouchers", voucherService.getAllVoucherByConditions());
        model.addAttribute("vouchers", voucherService.getAllVoucher());
        return "Admin/VoucherAdmin";
    }

    @GetMapping("add")
    public String showAddVoucher(Model model) {
        model.addAttribute("voucher", new Voucher());
        model.addAttribute("voucherTypes", VoucherType.values());
        return "Admin/AddVoucherAdmin";
    }
    @PostMapping("add")
    public String addVoucher(@ModelAttribute VoucherDTO voucher, Model model) {
        if(voucherService.addVoucher(voucher)){
            return "redirect:/admin/voucher";
        }
        model.addAttribute("error", "Voucher đã tồn tại !!! Mời bạn nhập lại!");
        return "Admin/AddVoucherAdmin";
    }
    @GetMapping("edit/{id}")
    public String showEditVoucher(@PathVariable String id, Model model) {
        Long voucherId = Long.parseLong(id);
        model.addAttribute("voucher", VoucherConverter.toVoucherDTO(voucherService.getVoucherById(voucherId)));
        model.addAttribute("voucherTypes", VoucherType.values());
        return "Admin/EditVoucherAdmin";
    }
    @PostMapping("edit")
    public String editVoucher(@ModelAttribute VoucherDTO voucher) {
        voucherService.updateVoucher(voucher);
        return "redirect:/admin/voucher";
    }
}
