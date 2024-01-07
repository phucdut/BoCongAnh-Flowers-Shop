package com.example.admin.Service.Impl;

import com.example.admin.Converter.ItemConverter;
import com.example.admin.Converter.VoucherConverter;
import com.example.admin.Domain.StaffDTO;
import com.example.admin.Domain.Voucher;
import com.example.admin.Domain.VoucherDTO;
import com.example.admin.Entity.FlashSaleEntity;
import com.example.admin.Entity.ItemEntity;
import com.example.admin.Entity.UserEntity;
import com.example.admin.Entity.VoucherEntity;
import com.example.admin.Repository.VoucherRepository;
import com.example.admin.Service.VoucherService;
import com.example.admin.enums.VoucherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Value("${imagePathProduct}")
    private String imagePath;
    @Override
    public List<Voucher>  getAllVoucher() {
        updateExpiredVoucherByDate();
        return voucherRepository.findAll().stream().map(VoucherConverter::toModel).toList();
    }
    @Override
    public boolean addVoucher(VoucherDTO voucher) {
        VoucherEntity voucherEntity = new VoucherEntity();
        Optional<VoucherEntity> optionalVoucher = voucherRepository.findAll().stream()
                .filter(entity -> entity.getCode().equals(voucher.getCode()))
                .findFirst();
        if (optionalVoucher.isPresent() || voucher.getStartDate() == null || voucher.getEndDate() == null ||
                voucher.getCode() == null || voucher.getUsageLimit() == 0) {
            return false;
        } else {
            updateExpiredVoucherByDate();
            voucherEntity.setConditionsPaymentOnline(true);
            voucherEntity.setId(voucher.getId());
            voucherEntity.setTitle(voucher.getTitle());
            voucherEntity.setCode(voucher.getCode());
            forSaveImage(voucher, voucherEntity);
            voucherEntity.setPercentage(voucher.getPercentage());
            voucherEntity.setUsageLimit(voucher.getUsageLimit());
            voucherEntity.setStartDate(voucher.getStartDate());
            voucherEntity.setEndDate(voucher.getEndDate());
            voucherEntity.setConditionPrice(voucher.getConditionPrice());
            voucherEntity.setType(voucher.getType());
            voucherRepository.save(voucherEntity);
            return true;
        }
    }
    @Override
    public Voucher getVoucherById(Long voucherId) {
        return VoucherConverter.toModel(voucherRepository.findById(voucherId).orElseThrow());
    }
    @Override
    public void updateVoucher(VoucherDTO voucher) {
//        System.out.println("Voucher ID: " + voucher.getId());
        VoucherEntity voucherEntity = voucherRepository.findById(voucher.getId()).orElseThrow();
        voucherEntity.setId(voucher.getId());
        if (voucher.getEndDate() != null){
            voucherEntity.setEndDate(voucher.getEndDate());
        }
        if(voucher.getStartDate() != null){
            voucherEntity.setStartDate(voucher.getStartDate());
        }
        if (voucher.getCode() != null){
            voucherEntity.setCode(voucher.getCode());
        }
        if (voucher.getIcon() != null){
            forSaveImage(voucher, voucherEntity);
        }
        voucherEntity.setPercentage(voucher.getPercentage());
        voucherEntity.setUsageLimit(voucher.getUsageLimit());


        voucherEntity.setConditionPrice(voucher.getConditionPrice());
        voucherEntity.setType(voucher.getType());
        voucherRepository.save(voucherEntity);
    }

    private void updateExpiredVoucherByDate() {
        List<VoucherEntity> voucherEntities = voucherRepository.findAllByExpiredFalse();
        List<VoucherEntity> voucherEntityList = voucherRepository.findAllByExpiredTrue();

        for (VoucherEntity voucherEntity: voucherEntities) {
            if (voucherEntity.getEndDate().isBefore(LocalDate.now()) || voucherEntity.getUsageLimit() == 0) {
                voucherEntity.setExpired(true);
                voucherRepository.save(voucherEntity);
            }
        }
        for (VoucherEntity voucher: voucherEntityList) {
            if (voucher.getEndDate().isAfter(LocalDate.now()) & voucher.getUsageLimit() != 0) {
                voucher.setExpired(false);
                voucherRepository.save(voucher);
            }
        }
    }
    private void forSaveImage(VoucherDTO voucherDTO, VoucherEntity entity) {
        File file = new File(imagePath + voucherDTO.getCode() + ".png");
        saveImage(voucherDTO.getIcon(), file);
        entity.setIcon(voucherDTO.getCode() + ".png");
    }

    private void saveImage(MultipartFile image, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(image.getBytes());
            // Thêm dòng in để kiểm tra
            System.out.println("File has been saved successfully");
        } catch (IOException e) {
            e.printStackTrace(); // Thêm dòng này để in ra thông báo lỗi chi tiết
        }
    }
}
