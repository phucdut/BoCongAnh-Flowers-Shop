package com.example.customer.controller.RESTfulAPI;

import com.example.customer.domain.OrderDetailHistory;
import com.example.customer.domain.OrderHistory;
import com.example.customer.service.OrderHistoryService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/order-history")
public class OrderAPIController {

    @Autowired
    private CustomerValidate customerValidate;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping()
    public ResponseEntity<List<OrderHistory>> getAllOrderHistory() {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderHistoryService.getOrderByCustomer(name));
    }

    @GetMapping("detail/{orderId}")
    public ResponseEntity<OrderHistory> getOrderHistory(@PathVariable Long orderId) {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderHistoryService.getOrderByOrderId(orderId, name));
    }

}
