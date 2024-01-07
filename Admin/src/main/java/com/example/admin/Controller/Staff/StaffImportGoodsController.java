package com.example.admin.Controller.Staff;

import com.example.admin.Domain.ImportGood;
import com.example.admin.Domain.ImportGoodsDto;
import com.example.admin.Service.ImportGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("staff")
@CrossOrigin("*")
public class StaffImportGoodsController {

    @Autowired
    private ImportGoodsService importGoodsService;

    @PostMapping("/add-import-good")
    public ResponseEntity<?> save(@RequestBody ImportGoodsDto importGoodsDto){
        importGoodsService.createByStaff(importGoodsDto);
        return new ResponseEntity<>("import success", HttpStatus.CREATED);
    }

    @GetMapping("/all-import-goods")
    public ResponseEntity<?> findAll(@RequestParam(value = "from", required = false) Date from,
                                     @RequestParam(value = "to", required = false) Date to){
        List<ImportGood> result = importGoodsService.getAllImportGoodsByTime(from, to);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/importgoods")
    public String importgoods(Model model) {
        return "Staff/importgood";
    }

    @GetMapping("/add-importgoods")
    public String addImportgoods(Model model) {
        return "Staff/addimportgood";
    }


}
