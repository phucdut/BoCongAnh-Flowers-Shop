package com.example.customer.responseBody;


import com.example.customer.domain.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private boolean success;

    private String message;

    private Customer result;
}
