package com.example.customer.requestBody;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CustomerRequest {

    private byte[] avatar;

    private String phone;

    private String fullName;

    private String email;

    private boolean sex;

    private LocalDate birthday;
}
