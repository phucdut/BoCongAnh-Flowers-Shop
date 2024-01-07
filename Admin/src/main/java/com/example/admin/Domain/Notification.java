package com.example.admin.Domain;

import com.example.admin.enums.TitleType;
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