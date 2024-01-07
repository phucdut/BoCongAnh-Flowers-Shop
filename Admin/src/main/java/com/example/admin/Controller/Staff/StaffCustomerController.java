package com.example.admin.Controller.Staff;

import com.example.admin.Domain.Customer;
import com.example.admin.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("staff")
public class StaffCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public String showStaffHome(Model model){
        return "Staff/StaffCustomer";
    }

    @GetMapping("/all-customer")
    public ResponseEntity<?> findAll(){
        List<Customer> result = customerService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
