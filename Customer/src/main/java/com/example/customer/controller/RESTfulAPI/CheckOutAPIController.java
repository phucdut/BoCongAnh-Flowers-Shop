package com.example.customer.controller.RESTfulAPI;

import com.example.customer.domain.Order;
import com.example.customer.service.OrderService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/check-out")
public class CheckOutAPIController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerValidate customerValidate;

    @PostMapping()
    public ResponseEntity<Order> checkout(@RequestBody Order orderRequest) {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Order order = orderService.checkoutOrder(orderRequest, name);
        return ResponseEntity.ok(order);
    }
}
