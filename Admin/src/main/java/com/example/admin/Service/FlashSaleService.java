package com.example.admin.Service;

import com.example.admin.Domain.FlashSale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlashSaleService {
    List<FlashSale> getAllFlashSale();

    boolean checkFlashSale(FlashSale flashSale);

    void addFlashSale(FlashSale flashSale);

    FlashSale getFlashSaleById(Long flashSaleId);

    void updateFlashSale(FlashSale flashSale);

    void deleteFlashSaleById(Long flashSaleId);

//    void restoreFlashSaleIdById(Long flashSaleId);
}
