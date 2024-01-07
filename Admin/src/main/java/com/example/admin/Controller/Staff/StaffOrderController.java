package com.example.admin.Controller.Staff;

import com.example.admin.Domain.Order;
import com.example.admin.Domain.OrderHistory;
import com.example.admin.Domain.OrderNote;
import com.example.admin.Entity.OrderEntity;
import com.example.admin.Entity.OrderHistoryEntity;
import com.example.admin.Service.OrderService;
import com.example.admin.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/staff")
@CrossOrigin("*")
public class StaffOrderController {

    @Autowired
     private OrderService orderService;

    @GetMapping("/order")
    public String listCategory(Model model) {
        return "Staff/order";
    }

    @GetMapping("/all-order")
    public ResponseEntity<?> findAllList(@RequestParam(value = "from", required = false) Date from,
                                         @RequestParam(value = "to", required = false) Date to,
                                         @RequestParam(value = "trangthai", required = false) String trangthai){
        List<OrderHistory> result = orderService.findAllListByStatus(from, to, trangthai);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add-order-note")
    public ResponseEntity<?> addNote(@RequestBody OrderNote orderNote){
        OrderHistoryEntity result = orderService.addNote(orderNote);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-order-by-id")
    public ResponseEntity<?> findById(@RequestParam Long id){
        OrderHistory result = orderService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-all-status-order")
    public ResponseEntity<?> getAllStatus(){
        List<String> result = Stream.of(OrderStatus.values())
            .map(Enum::name)
            .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/update-status-order")
    public ResponseEntity<?> updateStatusOrder(@RequestParam String orderStatusName,
                                               @RequestParam("id") Long orderId){
        OrderStatus orderStatus = null;
        for (OrderStatus o : OrderStatus.values()) {
            if(o.name().equals(orderStatusName)){
                orderStatus = o;
            }
        }
        orderService.updateStatusOrder(orderStatus,orderId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
