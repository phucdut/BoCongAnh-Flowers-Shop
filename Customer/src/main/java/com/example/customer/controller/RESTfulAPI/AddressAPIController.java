package com.example.customer.controller.RESTfulAPI;


import com.example.customer.domain.Address;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.service.AddressService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressAPIController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerValidate customerValidate;

    @GetMapping()
    public ResponseEntity<List<Address>> getAddressByCustomer() {
        String name = customerValidate.validateCustomer();
        if (name != null) {
            return ResponseEntity.ok(addressService.getAllAddressByCustomer(name));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("create-address")
    public ResponseEntity<BodyResponse> createAddress(@RequestBody Address address){
        String name = customerValidate.validateCustomer();
        BodyResponse response = new BodyResponse();
        if (name != null) {
            addressService.addAddress(name, address);
            response.setSuccess(true);
            response.setMessage("success");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("add-address")
    public ResponseEntity<Address> addAddress(@RequestBody Address address){
        String name = customerValidate.validateCustomer();
        if (name != null) {
            return ResponseEntity.ok(addressService.addAddress(name, address));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
