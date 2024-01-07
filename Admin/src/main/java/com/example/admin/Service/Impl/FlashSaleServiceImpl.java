package com.example.admin.Service.Impl;

import com.example.admin.Converter.FlashSaleConverter;
import com.example.admin.Converter.ProductConverter;
import com.example.admin.Domain.FlashSale;
import com.example.admin.Entity.FlashSaleEntity;
import com.example.admin.Entity.ProductEntity;
import com.example.admin.Repository.FlashSaleRepository;
import com.example.admin.Repository.ProductRepository;
import com.example.admin.Service.FlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlashSaleServiceImpl implements FlashSaleService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Override
    public List<FlashSale> getAllFlashSale() {
        updateExpiredFlashSaleByDate();
        return flashSaleRepository.findAll().stream().map(FlashSaleConverter::toModel).toList();
    }
    @Override
    public void addFlashSale(FlashSale flashSale) {
        updateExpiredFlashSaleByDate();
//        voucher.setExpired(false);
        flashSaleRepository.save(FlashSaleConverter.toEntity(flashSale));
    }
    @Override
    public FlashSale getFlashSaleById(Long flashSaleId) {
        return FlashSaleConverter.toModel(flashSaleRepository.findById(flashSaleId).orElseThrow());
    }
    @Override
    public void updateFlashSale(FlashSale flashSale) {
        FlashSaleEntity flashSaleEntity = flashSaleRepository.findById(flashSale.getId()).orElseThrow();
        flashSaleEntity.setPriceSale(flashSale.getPriceSale());
        flashSaleEntity.setSale(flashSale.getSale());
        flashSaleEntity.setSale(flashSale.getSale());
        flashSaleEntity.setEndDate(flashSale.getEndDate());
        flashSaleEntity.setStartDate(flashSale.getStartDate());

        flashSaleEntity.setProductEntity(ProductConverter.toEntity(flashSale.getProduct()));

        flashSaleRepository.save(flashSaleEntity);
    }
    @Override
    public void deleteFlashSaleById(Long flashSaleId) {
        FlashSaleEntity flashSaleEntity = flashSaleRepository.findById(flashSaleId).orElseThrow();
        flashSaleEntity.setExpired(true);
        flashSaleRepository.save(flashSaleEntity);
    }

//    @Override
//    public void restoreFlashSaleIdById(Long flashSaleId) {
//        FlashSaleEntity flashSaleEntity = flashSaleRepository.findById(flashSaleId).orElseThrow();
//        flashSaleEntity.setExpired(false);
//        flashSaleRepository.save(flashSaleEntity);
//    }
    @Override
    public boolean checkFlashSale(FlashSale flashSale) {
        ProductEntity productEntity = productRepository.findById(flashSale.getProduct().getId()).orElse(null);
        if (productEntity != null) {
            FlashSaleEntity flashSaleEntity = flashSaleRepository.findById(flashSale.getProduct().getId()).orElse(null);
            return flashSaleEntity == null;
        }
        return false;
    }
    private void updateExpiredFlashSaleByDate() {
        List<FlashSaleEntity> flashSaleEntities = flashSaleRepository.findAllByExpiredFalse();
        List<FlashSaleEntity> flashSaleEntityList = flashSaleRepository.findAllByExpiredTrue();

        for (FlashSaleEntity flashSaleEntity: flashSaleEntities) {
            if (flashSaleEntity.getEndDate().isBefore(LocalDate.now())) {
                flashSaleEntity.setExpired(true);
                flashSaleRepository.save(flashSaleEntity);
            }
        }
        for (FlashSaleEntity flashSale: flashSaleEntityList) {
            if (flashSale.getEndDate().isAfter(LocalDate.now())) {
                flashSale.setExpired(false);
                flashSaleRepository.save(flashSale);
            }
        }
    }
}
