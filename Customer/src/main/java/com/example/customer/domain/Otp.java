package com.example.customer.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Otp {
    private Long id;
    private String otp;
    private LocalDateTime otpExpiration;
}
