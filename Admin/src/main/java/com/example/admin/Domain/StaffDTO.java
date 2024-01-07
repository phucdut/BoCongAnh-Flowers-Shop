package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class StaffDTO {
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private MultipartFile image;

    private String phone;

    private String address;

    private LocalDate birthday;

    private String salary;

    private String role;

    private Boolean deleted;

    private List<Order> orders;
}
