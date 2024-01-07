package com.example.admin.Controller.Admin;

import com.example.admin.Domain.Review;
import com.example.admin.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.admin.Service.ReviewService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String listReview(Model model) {
        model.addAttribute("reviews", reviewService.getAllReview());
        model.addAttribute("customer", customerService.getAllCustomer());
        return "Admin/ReviewAdmin";
    }
    @GetMapping("detail/{id}")
    public String showDetailReview(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewService.getReviewById(id));
        return "Admin/DetailReviewAdmin";
    }
    @PostMapping("detail")
    public String detailReview(@ModelAttribute Review review) {
        reviewService.detailReview(review);
        return "redirect:/admin/review";
    }
}
