package com.example.admin.Controller.Staff;

import com.example.admin.Domain.OrderDetail;
import com.example.admin.Domain.OrderDetailHistory;
import com.example.admin.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/staff")
@CrossOrigin("*")
public class StaffOrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/order-detail-by-order")
    public ResponseEntity<?> findByOrder(@RequestParam("id") Long orderId){
        List<OrderDetailHistory> result = orderDetailService.findByOrder(orderId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
