package com.example.customer.converter;

import com.example.customer.domain.Customer;
import com.example.customer.entity.CustomerEntity;


public class CustomerConverter {

    public static Customer toModel(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setUsername(customerEntity.getUsername());
        if (customerEntity.getAvatar() == null) {
            customer.setAvatar(null);
        } else {
            customer.setAvatar(customerEntity.getAvatar());
        }
        customer.setPhone(customerEntity.getPhone());
        customer.setFullName(customerEntity.getFullName());
        customer.setEmail(customerEntity.getEmail());
        customer.setSex(customerEntity.isSex());
        customer.setBirthday(customerEntity.getBirthday());
        return customer;
    }

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setPhone(customer.getPhone());
        entity.setFullName(customer.getFullName());
        entity.setEmail(customer.getEmail());
        return entity;
    }
}
