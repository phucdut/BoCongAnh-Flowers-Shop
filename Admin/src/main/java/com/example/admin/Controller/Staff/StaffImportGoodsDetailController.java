package com.example.admin.Controller.Staff;

import com.example.admin.Entity.ImportGoodsDetailEntity;
import com.example.admin.Service.ImportGoodsDetailService;
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
@RequestMapping("staff")
@CrossOrigin("*")
public class StaffImportGoodsDetailController {

    @Autowired
    private ImportGoodsDetailService importGoodsDetailService;

    @GetMapping("/detail-import-good-by-import-good")
    public ResponseEntity<?> findByImportGood(@RequestParam("id") Long idImportGood){
        List<ImportGoodsDetailEntity> result = importGoodsDetailService.findByImportGood(idImportGood);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
