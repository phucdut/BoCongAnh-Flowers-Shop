package com.example.customer.controller;

import com.example.customer.responseBody.BodyResponse;
import com.example.customer.service.NotificationService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CustomerValidate customerValidate;

    @Transactional
    @GetMapping("success-web")
    public String success(@RequestHeader HttpHeaders headers) {
        String name = customerValidate.validateCustomer();
//        if (name == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        Long notifyId = notificationService.addNotifyPayment(name, true);
        notificationService.sendNotifyPayment(headers, notifyId, true);
        return "success";
    }

    @Transactional
    @GetMapping("failed-web")
    public String failed(@RequestHeader HttpHeaders headers) {
        String name = customerValidate.validateCustomer();
//        if (name == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        Long notifyId = notificationService.addNotifyPayment(name, false);
        notificationService.sendNotifyPayment(headers, notifyId, false);
        return "redirect:/cart";
    }
}
