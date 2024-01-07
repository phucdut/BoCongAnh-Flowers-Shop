package com.example.customer.service;

import com.example.customer.domain.Customer;
import com.example.customer.requestBody.CustomerRequest;
import com.example.customer.requestBody.PasswordRequest;

import java.io.IOException;

public interface CustomerService {
    Customer checkCustomer(String username, String password);

    boolean checkUsername(String username);

    void createCustomer(Customer customer);

    void changePassword(Customer customer);

    Customer getCustomerByUsername(String name);

    Customer updateCustomer(String name, CustomerRequest newCustomer);

    boolean updatePassword(String name, PasswordRequest passwordRequest);

    void saveTokenAndCookie(Customer customer, String cookie);

    String getCustomerByUserId(Long userId);

    void saveToken(String name, String token);
}
