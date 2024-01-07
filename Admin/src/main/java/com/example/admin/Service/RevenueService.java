package com.example.admin.Service;

import com.example.admin.Domain.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface RevenueService {
    List<Revenue> getAllRevenue();

    void updateTotalRevenue(LocalDate date);
}
