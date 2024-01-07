package com.example.customer.controller.RESTfulAPI;


import com.example.customer.domain.Customer;
import com.example.customer.requestBody.CustomerRequest;
import com.example.customer.requestBody.PasswordRequest;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.responseBody.CustomerResponse;
import com.example.customer.service.CustomerService;
import com.example.customer.validator.CustomerValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customer")
public class CustomerAPIController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidate customerValidate;

    @GetMapping
    public ResponseEntity<CustomerResponse> viewInfo() {
        System.out.println("in");
        String name = customerValidate.validateCustomer();
        if (name != null) {
            Customer customer = customerService.getCustomerByUsername(name);
            CustomerResponse customerResponse = new CustomerResponse();
            if (customer != null) {
                customerResponse.setSuccess(true);
                customerResponse.setMessage("success");
                customerResponse.setResult(customer);
            } else {
                customerResponse.setSuccess(false);
                customerResponse.setMessage("Invalid username or password");
            }
            return ResponseEntity.ok().body(customerResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("update")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest newCustomer) {
        CustomerResponse customerResponse = new CustomerResponse();
        String name = customerValidate.validateCustomer();
        if (name != null ) {
            Customer customer = customerService.updateCustomer(name, newCustomer);
            customerResponse.setSuccess(true);
            customerResponse.setMessage("success");
            customerResponse.setResult(customer);
            return ResponseEntity.ok(customerResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("change-password")
    public ResponseEntity<BodyResponse> changePassword(@RequestBody PasswordRequest passwordRequest) {
        String name = customerValidate.validateCustomer();
        if (name == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        BodyResponse response = new BodyResponse();
        if (customerService.updatePassword(name, passwordRequest)) {
            response.setSuccess(true);
            response.setMessage("success");

        } else {
            response.setSuccess(false);
            response.setMessage("incorrect password");
        }
        return ResponseEntity.ok(response);

    }
}
