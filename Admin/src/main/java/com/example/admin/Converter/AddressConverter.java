package com.example.admin.Converter;

import com.example.admin.Domain.Address;
import com.example.admin.Domain.Review;
import com.example.admin.Entity.AddressEntity;
import com.example.admin.Entity.ReviewEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AddressConverter {
    public static Address toModel(AddressEntity addressEntity) {
        Address address = new Address();
        address.setId(addressEntity.getId());
        address.setCity(addressEntity.getNameCity());
        address.setDistrict(addressEntity.getNameDistrict());
        address.setWard(addressEntity.getNameWard());
        address.setStreet(addressEntity.getStreet());
        address.setNameCustomer(addressEntity.getNameCustomer());
        address.setPhoneNumber(addressEntity.getPhoneNumber());
        address.setCustomerId(addressEntity.getCustomerEntity().getId());
        return address;
    }
}
