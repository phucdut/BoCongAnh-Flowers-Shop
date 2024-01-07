package com.example.customer.service;

import com.example.customer.domain.Otp;

public interface OtpService {
    boolean verifyOTP(Otp otp);
}
