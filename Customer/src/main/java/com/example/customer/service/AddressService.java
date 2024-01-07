package com.example.customer.service;

import com.example.customer.domain.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddressByCustomer(String name);
    Address addAddress(String name, Address address);
}
