package com.example.customer.validator;

import com.example.customer.entity.CustomerEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidate {
    public String validateCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser") ||!authentication.isAuthenticated()) {
            return null;
        }
        return authentication.getName();
    }
}
