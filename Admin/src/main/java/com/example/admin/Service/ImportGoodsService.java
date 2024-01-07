package com.example.admin.Service;

import com.example.admin.Domain.ImportGood;
import com.example.admin.Domain.ImportGoodsDto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface ImportGoodsService {
    List<ImportGood> getImportGoodByTime(LocalDateTime startTime, LocalDateTime endTime);
    List<ImportGood> getAllImportGoods();
    List<ImportGood> getAllImportGoodsByTime(Date from, Date to);
    Long getTotalImportAmountByTime(LocalDateTime startTime, LocalDateTime endTime);
    void addImportGoods(ImportGood importGood);
    void createByStaff(ImportGoodsDto importGoodsDto);
}
