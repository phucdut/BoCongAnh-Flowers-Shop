package com.example.admin.Service;

import com.example.admin.Domain.Customer;
import com.example.admin.Entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();

//    void addCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    void detailCustomer(Customer customer);

    List<Customer> findAll();

//    void deleteCustomerById(Long customerId);
//
//    void restoreCustomerById(Long customerId);
}
