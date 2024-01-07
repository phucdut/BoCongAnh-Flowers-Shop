package com.example.customer.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
    private String subject;

    private String message;

}
