package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TimeRange {
    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
