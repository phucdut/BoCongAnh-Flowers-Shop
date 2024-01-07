package com.example.customer.service;


import com.example.customer.entity.CustomerEntity;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerEntity entity = customerRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Không tìm thấy user"));
        UserPrincipal principal = new UserPrincipal();
        principal.setId(entity.getId());
        principal.setUsername(username);
        principal.setPassword(entity.getPassword());
        return principal;

    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("1"));
    }
}
