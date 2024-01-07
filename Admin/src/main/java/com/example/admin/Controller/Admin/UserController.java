package com.example.admin.Controller.Admin;

import com.example.admin.Converter.UserConverter;
import com.example.admin.Domain.StaffDTO;
import com.example.admin.Domain.User;
import com.example.admin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/staff")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String listStaff(Model model) {
        model.addAttribute("users", userService.getAllStaff());
        return "Admin/StaffAdmin";
    }

    @GetMapping("add")
    public String showAddStaff(Model model) {
        model.addAttribute("user", new User());
        return "Admin/AddStaffAdmin";
    }
    @PostMapping("add")
    public String addStaff(@ModelAttribute StaffDTO user, Model model) {
        if(userService.addStaff(user)){
            return "redirect:/admin/staff";
        }
        model.addAttribute("error", "Staff đã tồn tại !!! Mời bạn nhập lại!");
        return "Admin/AddStaffAdmin";
    }
    @GetMapping("detail/{id}")
    public String showDetailStaff(@PathVariable String id, Model model) {
        Long userId = Long.parseLong(id);
        model.addAttribute("user", userService.getStaffById(userId));
        return "Admin/ViewDetailStaffAdmin";
    }
    @PostMapping("detail")
    public String detailStaff(@ModelAttribute User user) {
        userService.detailStaff(user);
        return "redirect:/admin/staff";
    }
    @GetMapping("edit/{id}")
    public String showEditStaff(@PathVariable String id, Model model) {
        Long userId = Long.parseLong(id);
        model.addAttribute("user", UserConverter.toStaffDTO(userService.getStaffById(userId)));
        return "Admin/EditStaffAdmin";
    }
    @PostMapping("edit")
    public String editStaff(@ModelAttribute StaffDTO user) {
        userService.updateStaff(user);
        return "redirect:/admin/staff";
    }

    @GetMapping("delete/{id}")
    public String deleteStaff(@PathVariable String id) {
        Long userId = Long.parseLong(id);
        userService.deleteStaffById(userId);
        return "redirect:/admin/staff";
    }
    @GetMapping("restore")
    public String listStaffRestore(Model model) {
        model.addAttribute("users", userService.getAllStaff());
        return "Admin/RestoreStaffAdmin";
    }
    @GetMapping("restore/{id}")
    public String restoreStaff(@PathVariable String id) {
        Long userId = Long.parseLong(id);
        userService.restoreStaffById(userId);
        return "redirect:/admin/staff/restore";
    }
    @CrossOrigin
    @GetMapping("reset-password/{id}")
    public ResponseEntity<String> ResetPassword(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.resetPassword(id);
        redirectAttributes.addAttribute("id", id);
        String redirectUrl = "/admin/staff/detail/" + id;
        return ResponseEntity.ok(redirectUrl);
    }

    @GetMapping("send-message")
    public String sendMess() {
        return "Admin/SendMessageAdmin";
    }
}
