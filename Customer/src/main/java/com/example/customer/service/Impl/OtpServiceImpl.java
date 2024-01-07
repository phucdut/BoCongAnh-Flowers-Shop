package com.example.customer.service.Impl;

import com.example.customer.domain.Otp;
import com.example.customer.entity.OTPEntity;
import com.example.customer.repository.OtpRepository;
import com.example.customer.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public boolean verifyOTP(Otp otp) {
        OTPEntity otpEntity = otpRepository.findById(otp.getId()).orElse(null);
        if (otpEntity != null) {
            // Kiểm tra xem mã OTP có khớp không
            return otpEntity.getOtp().equals(otp.getOtp()) && LocalDateTime.now().isBefore(otpEntity.getOtpExpiration());
        }
        return false;
    }
}
