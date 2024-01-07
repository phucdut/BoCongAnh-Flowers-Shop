package com.example.admin.Controller.Admin;

import com.example.admin.Domain.Address;
import com.example.admin.Domain.Customer;
import com.example.admin.Service.AddressService;
import com.example.admin.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("admin/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @GetMapping()
    public String listCustomer(Model model) {
        model.addAttribute("customers", customerService.getAllCustomer());
        model.addAttribute("address", addressService.getAllAddress());
        return "Admin/CustomerAdmin";
    }
    @GetMapping("detail/{id}")
    public String showDetailCustomer(@PathVariable String id, Model model) {
        Long customerId = Long.parseLong(id);
        model.addAttribute("customer", customerService.getCustomerById(customerId));
        model.addAttribute("address", addressService.getAllAddress());
        return "Admin/DetailCustomerAdmin";
    }
    @PostMapping("detail")
    public String detailCustomer(@ModelAttribute Customer customer) {
        customerService.detailCustomer(customer);
        return "redirect:/admin/customer";
    }
}
