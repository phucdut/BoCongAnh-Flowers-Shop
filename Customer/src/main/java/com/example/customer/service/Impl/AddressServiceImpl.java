package com.example.customer.service.Impl;

import com.example.customer.converter.AddressConverter;
import com.example.customer.domain.Address;
import com.example.customer.entity.AddressEntity;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.repository.*;
import com.example.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddressByCustomer(String name) {
        return addressRepository.findAllByCustomerEntity(
                customerRepository.findByUsername(name).orElseThrow()).stream().map(AddressConverter::toModel).toList();
    }

    @Override
    public Address addAddress(String name, Address address) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCustomerEntity(customerEntity);
        addressEntity.setNameCity(address.getCity());
        addressEntity.setNameDistrict(address.getDistrict());
        addressEntity.setNameWard(address.getWard());
        addressEntity.setNameCustomer(address.getNameCustomer());
        addressEntity.setPhoneNumber(address.getPhoneNumber());
        addressEntity.setStreet(address.getStreet());
        return AddressConverter.toModel(addressRepository.save(addressEntity));
    }
}
