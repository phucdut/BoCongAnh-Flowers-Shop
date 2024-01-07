package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private Long id;

    private String name;

    private List<Product> products;

    private MultipartFile image;

    private boolean deleted;

    private String detail;
}
