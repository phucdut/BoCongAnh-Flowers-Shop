package com.example.customer.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
}
