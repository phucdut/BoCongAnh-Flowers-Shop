package com.example.customer.controller.RESTfulAPI;

import com.example.customer.domain.Voucher;
import com.example.customer.service.VoucherService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/voucher")
public class VoucherAPIController {
    @Autowired
    private CustomerValidate customerValidate;
    @Autowired
    private VoucherService voucherService;
    @GetMapping()
    public ResponseEntity<List<Voucher>> getVoucher() {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(voucherService.getAllVoucherByConditions());
    }
}
