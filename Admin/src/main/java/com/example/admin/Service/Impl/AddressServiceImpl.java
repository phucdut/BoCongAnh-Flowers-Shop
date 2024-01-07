package com.example.admin.Service.Impl;

import com.example.admin.Converter.AddressConverter;
import com.example.admin.Domain.Address;
import com.example.admin.Repository.AddressRepository;
import com.example.admin.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddress(){
        return addressRepository.findAll().stream().map(AddressConverter::toModel).toList();
    }
}
