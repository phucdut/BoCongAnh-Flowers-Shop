package com.example.customer.controller.RESTfulAPI;


import com.example.customer.domain.CartItem;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.service.CartService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/cart")
public class CartAPIController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerValidate customerValidate;

    @GetMapping()
    public ResponseEntity<List<CartItem>> viewCart() {
        String name = customerValidate.validateCustomer();
        if (name != null){
            return ResponseEntity.ok(cartService.getCartByUsernameCustomer(name).getCartItems());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("add/{id}")
    public ResponseEntity<BodyResponse> addItem(@PathVariable Long id) {
        String name = customerValidate.validateCustomer();
        BodyResponse response = new BodyResponse();
        if (name != null) {
            cartService.addItem(name ,id);
            response.setSuccess(true);
            response.setMessage("success");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("update")
    public ResponseEntity<BodyResponse> updateCart(@RequestBody CartItem cartItem) {
        String name = customerValidate.validateCustomer();
        BodyResponse response = new BodyResponse();
        if (name != null){
            if (cartService.updateQuantityItem(name, cartItem)) {
                response.setSuccess(true);
                response.setMessage("success");
            } else {
                response.setSuccess(false);
                response.setMessage("Item does not exist");
            }
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<BodyResponse> deleteItem(@PathVariable Long id) {
        String name = customerValidate.validateCustomer();
        BodyResponse response = new BodyResponse();
        if (name != null) {
            if (cartService.deleteItem(name ,id)) {
                response.setSuccess(true);
                response.setMessage("success");
            } else {
                response.setSuccess(false);
                response.setMessage("Item does not exist");
            }
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
