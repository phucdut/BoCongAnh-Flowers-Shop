package com.example.customer.service.Impl;

import com.example.customer.entity.OTPEntity;
import com.example.customer.repository.OtpRepository;
import com.example.customer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Long sendEmail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject("Mã OTP xác nhận Email");
        String otp = generateOTP();
        simpleMailMessage.setText("OTP: " + otp);
        simpleMailMessage.setTo(email);
        sender.send(simpleMailMessage);
        OTPEntity otpEntity = new OTPEntity();
        otpEntity.setOtp(otp);
        otpEntity.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
        otpEntity = otpRepository.save(otpEntity);
        return otpEntity.getId();
    }

    public static String generateOTP() {
        Random random = new Random();
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
