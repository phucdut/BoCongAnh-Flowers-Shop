package com.example.admin.Service;

import com.example.admin.Domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<Address> getAllAddress();
}
