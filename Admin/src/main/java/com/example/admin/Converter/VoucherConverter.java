package com.example.admin.Converter;

import com.example.admin.Domain.StaffDTO;
import com.example.admin.Domain.User;
import com.example.admin.Domain.Voucher;
import com.example.admin.Domain.VoucherDTO;
import com.example.admin.Entity.VoucherEntity;

public class VoucherConverter {
    public static Voucher toModel(VoucherEntity voucherEntity) {
        Voucher voucher = new Voucher();
        voucher.setId(voucherEntity.getId());
        voucher.setTitle(voucherEntity.getTitle());
        voucher.setCode(voucherEntity.getCode());
        voucher.setIcon(voucherEntity.getIcon());
        voucher.setConditionsPaymentOnline(voucherEntity.isConditionsPaymentOnline());
        voucher.setPercentage(voucherEntity.getPercentage());
        voucher.setUsageLimit(voucherEntity.getUsageLimit());
        voucher.setStartDate(voucherEntity.getStartDate());
        voucher.setEndDate(voucherEntity.getEndDate());
        voucher.setConditionPrice(voucherEntity.getConditionPrice());
        voucher.setExpired(voucherEntity.isExpired());
        return voucher;
    }
    public static VoucherEntity toEntity(Voucher voucher){
        VoucherEntity voucherEntity = new VoucherEntity();
        voucherEntity.setId(voucher.getId());
        voucherEntity.setTitle(voucher.getTitle());
        voucherEntity.setCode(voucher.getCode());
        voucherEntity.setIcon(voucher.getIcon());
        voucherEntity.setConditionsPaymentOnline(voucher.isConditionsPaymentOnline());
        voucherEntity.setPercentage(voucher.getPercentage());
        voucherEntity.setUsageLimit(voucher.getUsageLimit());
        voucherEntity.setStartDate(voucher.getStartDate());
        voucherEntity.setEndDate(voucher.getEndDate());
        voucherEntity.setConditionPrice(voucher.getConditionPrice());
        voucherEntity.setExpired(voucher.isExpired());
        voucherEntity.setType(voucher.getType());
        return voucherEntity;
    }
    public static VoucherDTO toVoucherDTO(Voucher voucher) {
        VoucherDTO dto = new VoucherDTO();;
        dto.setId(voucher.getId());
        dto.setTitle(voucher.getTitle());
        dto.setCode(voucher.getCode());
        dto.setConditionsPaymentOnline(voucher.isConditionsPaymentOnline());
        dto.setPercentage(voucher.getPercentage());
        dto.setUsageLimit(voucher.getUsageLimit());
        dto.setStartDate(voucher.getStartDate());
        dto.setEndDate(voucher.getEndDate());
        dto.setConditionPrice(voucher.getConditionPrice());
        dto.setExpired(voucher.isExpired());
        dto.setType(voucher.getType());
        return dto;
    }
}