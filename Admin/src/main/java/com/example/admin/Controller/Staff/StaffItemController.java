package com.example.admin.Controller.Staff;

import com.example.admin.Domain.Item;
import com.example.admin.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("staff")
@CrossOrigin("*")
public class StaffItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/all-item")
    public ResponseEntity<?> findAll(){
        List<Item> result = itemService.getAllItem();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
