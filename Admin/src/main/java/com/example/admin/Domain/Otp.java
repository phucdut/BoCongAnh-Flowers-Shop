package com.example.admin.Domain;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class Otp {
    private Long id;

    private String otp;

    private LocalDateTime otpExpiration;
}
