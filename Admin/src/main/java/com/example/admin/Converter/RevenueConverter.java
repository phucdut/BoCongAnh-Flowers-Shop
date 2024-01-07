package com.example.admin.Converter;

import com.example.admin.Domain.Revenue;
import com.example.admin.Entity.RevenueEntity;

public class RevenueConverter {
    public static Revenue toModel(RevenueEntity revenueEntity){
        Revenue revenue = new Revenue();
        revenue.setId(revenueEntity.getId());
        revenue.setDate(revenueEntity.getDate());
        revenue.setTotalRevenue(revenueEntity.getTotalRevenue());

        return revenue;
    }
}
