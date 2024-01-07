package com.example.customer.controller.RESTfulAPI;


import com.example.customer.domain.Customer;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.service.CustomerService;
import com.example.customer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/verify-email")
public class MailAPIController {

    @Autowired
    private MailService mailService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("register")
    public ResponseEntity<BodyResponse> verifyEmail(@RequestBody Customer customer) {
        BodyResponse emailResponse = new BodyResponse();
        if (customerService.checkUsername(customer.getUsername())) {
            Long otpCode = mailService.sendEmail(customer.getEmail());
            emailResponse.setSuccess(true);
            emailResponse.setMessage(otpCode.toString());
        } else {
            emailResponse.setSuccess(false);
            emailResponse.setMessage("Account name has been taken");
        }
        return ResponseEntity.ok().body(emailResponse);
    }

    @PostMapping("forgot")
    public ResponseEntity<BodyResponse> verifyEmailForgot(@RequestBody Customer customer) {
        BodyResponse emailResponse = new BodyResponse();
        Customer customerTrue = customerService.getCustomerByUsername(customer.getUsername());
        if (customerTrue != null) {
            Long otpCode = mailService.sendEmail(customerTrue.getEmail());
            emailResponse.setSuccess(true);
            emailResponse.setMessage(otpCode.toString());
        } else {
            emailResponse.setSuccess(false);
            emailResponse.setMessage("Account name has been taken");
        }
        return ResponseEntity.ok().body(emailResponse);
    }
}
