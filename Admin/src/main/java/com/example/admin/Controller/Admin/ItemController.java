package com.example.admin.Controller.Admin;

import com.example.admin.Domain.Category;
import com.example.admin.Service.Impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.admin.Domain.Item;
import com.example.admin.Service.ItemService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping()
    public String listItem(Model model) {
        model.addAttribute("items", itemService.getAllItem());
        return "Admin/ItemAdmin";
    }
    @GetMapping("add")
    public String showAddItem(Model model) {
        model.addAttribute("item", new Item());
        return "Admin/AddItemAdmin";
    }
    @PostMapping("add")
    public String addItem(@ModelAttribute Item item, Model model) {
        if (itemService.addItem(item)) {
            return "redirect:/admin/item";
        }
        model.addAttribute("error", "Item đã tồn tại !!! Mời bạn nhập lại!");
        return "Admin/AddItemAdmin";

    }
    @GetMapping("edit/{id}")
    public String showEditItem(@PathVariable String id, Model model) {
        Long itemId = Long.parseLong(id);
        model.addAttribute("item", itemService.getItemById(itemId));
        return "Admin/EditItemAdmin";
    }
    @PostMapping("edit")
    public String editItem(@ModelAttribute Item item) {
        itemService.updateItem(item);
        return "redirect:/admin/item";
    }

    @GetMapping("delete/{id}")
    public String deleteItem(@PathVariable String id) {
        Long itemId = Long.parseLong(id);
        itemService.deleteItemById(itemId);
        return "redirect:/admin/item";
    }

    @GetMapping("restore")
    public String listItemRestore(Model model) {
        model.addAttribute("items", itemService.getAllItem());
        return "Admin/RestoreItemAdmin";
    }
    @GetMapping("restore/{id}")
    public String restoreItem(@PathVariable String id) {
        Long itemId = Long.parseLong(id);
        itemService.restoreItemById(itemId);
        return "redirect:/admin/item/restore";
    }
}
