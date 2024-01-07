package com.example.admin.Service.Impl;

import com.example.admin.Converter.ImportGoodsConverter;
import com.example.admin.Domain.ImportGood;
import com.example.admin.Domain.ImportGoodsDetailDto;
import com.example.admin.Domain.ImportGoodsDto;
import com.example.admin.Entity.ImportGoodsDetailEntity;
import com.example.admin.Entity.ImportGoodsEntity;
import com.example.admin.Entity.ItemEntity;
import com.example.admin.Repository.ImportGoodsDetailRepository;
import com.example.admin.Repository.ImportGoodsRepository;
import com.example.admin.Service.ImportGoodsService;
import com.example.admin.Service.UserService;
import com.example.admin.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ImportGoodsServiceImpl implements ImportGoodsService {
    @Autowired
    private ImportGoodsRepository importGoodsRepository;

    @Autowired
    private ImportGoodsDetailRepository importGoodsDetailRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<ImportGood> getAllImportGoods() {
        return importGoodsRepository.findAll().stream().map(ImportGoodsConverter::toModel).toList();
    }

    @Override
    public List<ImportGood> getAllImportGoodsByTime(Date from, Date to) {
        if(from == null || to == null){
            from = Date.valueOf("2000-01-01");
            to = Date.valueOf("2100-01-01");
        }
        LocalTime s = LocalTime.of(0,0);
        LocalDateTime frl = LocalDateTime.of(from.toLocalDate(), s);
        LocalDateTime tol = LocalDateTime.of(to.toLocalDate(), s);

        return importGoodsRepository.findByDate(frl, tol).stream().map(ImportGoodsConverter::toModel).toList();
    }

    @Override
    public List<ImportGood> getImportGoodByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return importGoodsRepository.findImportGoodsEntitiesByTimeImportBetween(startTime, endTime).stream().map(ImportGoodsConverter::toModel).toList();
    }
    public Long getTotalImportAmountByTime(LocalDateTime startTime, LocalDateTime endTime) {
        try {
            Long totalImportAmount = importGoodsRepository.getTotalImportAmountByTime(startTime, endTime);
            return totalImportAmount != null ? totalImportAmount.longValue() : 0L;
        } catch (Exception ex) {
            // Handle the exception or log it
            return 0L;
        }
    }

    @Override
    public void addImportGoods(ImportGood importGood) {
        importGoodsRepository.save(ImportGoodsConverter.toEntity(importGood));
    }

    @Override
    public void createByStaff(ImportGoodsDto importGoodsDto) {
        if(importGoodsDto.getImportGoodDetails().size() == 0){
            throw new MessageException("Không có item nào được chọn");
        }
        Long total = 0L;
        for(ImportGoodsDetailDto i : importGoodsDto.getImportGoodDetails()){
            total += i.getPrice()* i.getQuantity();
        }


        ImportGoodsEntity importGood = new ImportGoodsEntity();
        importGood.setTimeImport(LocalDateTime.now());
        importGood.setTotalPrice(total);
        importGood.setStaff_id(userService.getUserWithAuthority().get().getId());
        ImportGoodsEntity result = importGoodsRepository.save(importGood);

        for(ImportGoodsDetailDto i : importGoodsDto.getImportGoodDetails()){
            ImportGoodsDetailEntity detail = new ImportGoodsDetailEntity();
            detail.setImportGoodsEntity(result);
            ItemEntity item = new ItemEntity();
            item.setId(i.getIdItem());
            detail.setItemEntity(item);
            detail.setQuantity(i.getQuantity());
            detail.setItemPrice(i.getPrice());
            importGoodsDetailRepository.save(detail);
        }

    }
}
