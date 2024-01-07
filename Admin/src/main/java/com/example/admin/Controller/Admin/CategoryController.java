package com.example.admin.Controller.Admin;

import com.example.admin.Converter.CategoryConverter;
import com.example.admin.Domain.Category;
import com.example.admin.Domain.CategoryDTO;
import com.example.admin.Service.CategoryService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String listCategory(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "Admin/CategoryAdmin";
    }
    @GetMapping("add")
    public String showAddCategory(Model model) {
        model.addAttribute("category", new Category());
        return "Admin/AddCategoryAdmin";
    }

    @PostMapping("add")
    public String addCategory(@ModelAttribute CategoryDTO category, Model model) {
        if(categoryService.addCategory(category)){
            return "redirect:/admin/category";
        }
        model.addAttribute("error", "Category đã tồn tại !!! Mời bạn nhập lại!");
        return "Admin/AddCategoryAdmin";
    }
    @GetMapping("edit/{id}")
    public String showEditCategory(@PathVariable String id, Model model) {
        Long categoryId = Long.parseLong(id);
        model.addAttribute("category", CategoryConverter.toCategoryDTO(categoryService.getCategoryById(categoryId)));
        return "Admin/EditCategoryAdmin";
    }

    @PostMapping("edit")
    public String editCategory(@ModelAttribute CategoryDTO category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        Long categoryId = Long.parseLong(id);
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/admin/category";
    }

    @GetMapping("restore")
    public String listCategoryRestore(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "Admin/RestoreCategoryAdmin";
    }

    @GetMapping("restore/{id}")
    public String restoreCategory(@PathVariable String id) {
        Long categoryId = Long.parseLong(id);
        categoryService.restoreCategoryById(categoryId);
        return "redirect:/admin/category/restore";
    }
}
