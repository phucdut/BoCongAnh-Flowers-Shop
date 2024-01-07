package com.example.customer.controller.RESTfulAPI;


import com.example.customer.domain.Customer;
import com.example.customer.remote.LoginRemote;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.responseBody.CustomerResponse;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login-customer")
public class LoginAPIController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoginRemote loginRemote;

    @PostMapping()
    public ResponseEntity<CustomerResponse> login(@RequestBody Customer customer) {
        Customer customerTrue = customerService.checkCustomer(customer.getUsername(), customer.getPassword());
        CustomerResponse customerResponse = new CustomerResponse();
        HttpHeaders responseHeaders = new HttpHeaders();


        if (customerTrue != null) {
            HttpHeaders loginHeaders = loginRemote.sendPostRequestLogin(customer.getUsername(), customer.getPassword());

            // Set cookie if present
            if (loginHeaders.containsKey("Set-Cookie")) {
                String cookie = loginHeaders.getFirst("Set-Cookie");
                customerService.saveTokenAndCookie(customer, cookie);
                responseHeaders.set("Set-Cookie", cookie);
            }

            customerResponse.setSuccess(true);
            customerResponse.setMessage("success");
            customerResponse.setResult(customerTrue);
        } else {
            customerResponse.setSuccess(false);
            customerResponse.setMessage("Invalid username or password");
        }

        // Return the ResponseEntity with the custom headers and the body
        return ResponseEntity.ok().headers(responseHeaders).body(customerResponse);
    }

    @PostMapping("token")
    public ResponseEntity<BodyResponse> saveToken(@RequestBody String token) {
        BodyResponse bodyResponse = new BodyResponse();
        System.out.println(token);
        bodyResponse.setSuccess(true);
        bodyResponse.setMessage("success");
        return ResponseEntity.ok(bodyResponse);
    }

}
