package com.example.customer.controller.RESTfulAPI;


import com.example.customer.domain.Otp;
import com.example.customer.responseBody.BodyResponse;
import com.example.customer.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/verify")
public class VerifyOPTController {

    @Autowired
    private OtpService otpService;

    @PostMapping("otp")
    public ResponseEntity<BodyResponse> verifyOTP(@RequestBody Otp otp) {
        BodyResponse response = new BodyResponse();
        if (otpService.verifyOTP(otp)) {
            response.setSuccess(true);
            response.setMessage("success");
        } else {
            response.setSuccess(false);
            response.setMessage("incorrect OTP");
        }
        return ResponseEntity.ok(response);
    }
}
