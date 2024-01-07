package com.example.customer.domain;

import com.example.customer.enums.TitleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notification {
    private Long id;
    private TitleType type;
    private String title;
    private String content;
    private String image;
    private LocalDateTime datetime;
    private boolean haveRead;
}
