package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private Long id;

    private String name;

    private Long originalPrice;

    private Long price;

    private Long discount;

    private double overall_rating;

    private String description;

    private String details;

    private String delivery;

    private String subInfo;

    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;
    private MultipartFile image4;
    private MultipartFile image5;

    private List<Long> categoryIds;
}
