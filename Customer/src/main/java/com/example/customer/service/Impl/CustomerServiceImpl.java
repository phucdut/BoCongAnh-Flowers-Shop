package com.example.customer.service.Impl;

import com.example.customer.converter.CustomerConverter;
import com.example.customer.domain.Customer;
import com.example.customer.entity.CartEntity;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.repository.CartRepository;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.requestBody.CustomerRequest;
import com.example.customer.requestBody.PasswordRequest;
import com.example.customer.service.CustomerService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartRepository cartRepository;

    @Value("${imagePath}")
    private String imagePath;

    @Value("${imagePathCustomer}")
    private String imagePathCustomer;

    @Override
    public Customer checkCustomer(String username, String password) {
        CustomerEntity customerEntity = customerRepository.findByUsername(username).orElse(null);
        if (customerEntity != null && passwordEncoder.matches(password, customerEntity.getPassword())) {
            return CustomerConverter.toModel(customerEntity);
        }
        return null;
    }

    @Override
    public boolean checkUsername(String username) {
        CustomerEntity customerEntity = customerRepository.findByUsername(username).orElse(null);
        return customerEntity == null;
    }

    @Override
    public void createCustomer(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUsername(customer.getUsername());
        customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setPhone(customer.getPhone());
        customerEntity = customerRepository.save(customerEntity);
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCustomerEntity(customerEntity);
        cartRepository.save(cartEntity);
    }

    @Override
    public void changePassword(Customer customer) {
        CustomerEntity customerEntity = customerRepository.findByUsername(customer.getUsername()).orElse(null);
        if (customerEntity != null) {
            customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customerEntity);
        }
    }

    @Override
    public Customer getCustomerByUsername(String name) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElse(null);
        if (customerEntity != null) {
             return CustomerConverter.toModel(customerEntity);
        }
        return null;
    }

    @Override
    public Customer updateCustomer(String name, CustomerRequest newCustomer) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        customerEntity.setPhone(newCustomer.getPhone());
        customerEntity.setFullName(newCustomer.getFullName());
        customerEntity.setEmail(newCustomer.getEmail());
        customerEntity.setSex(newCustomer.isSex());
        customerEntity.setBirthday(newCustomer.getBirthday());
        if (newCustomer.getAvatar() != null) {
            File avatarFile = new File(imagePath + "imagesCustomer/" + customerEntity.getId() + ".png");
            // Tiếp tục xử lý upload file như bạn đã làm trong đoạn mã trước đó
            try (OutputStream os = new FileOutputStream(avatarFile)) {
                os.write(newCustomer.getAvatar());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            customerEntity.setAvatar(customerEntity.getId().toString() + ".png");

        }
        CustomerEntity returnCustomer = customerRepository.save(customerEntity);
        return CustomerConverter.toModel(returnCustomer);
    }

    @Override
    public boolean updatePassword(String name, PasswordRequest passwordRequest) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElse(null);
        if (customerEntity != null && passwordEncoder.matches(passwordRequest.getOldPassword(), customerEntity.getPassword())) {
            customerEntity.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            customerRepository.save(customerEntity);
            return true;
        }
        return false;
    }

    @Override
    public void saveTokenAndCookie(Customer customer, String cookie) {
        CustomerEntity customerEntity = customerRepository.findByUsername(customer.getUsername()).orElseThrow();
        customerEntity.setToken(customer.getToken());
        customerEntity.setCookie(cookie);
        customerRepository.save(customerEntity);
    }

    @Override
    public String getCustomerByUserId(Long userId) {
        return customerRepository.findById(userId).orElseThrow().getUsername();
    }

    @Override
    public void saveToken(String name, String token) {
        CustomerEntity customerEntity = customerRepository.findByUsername(name).orElseThrow();
        customerEntity.setToken(token);
        customerRepository.save(customerEntity);
    }
}
