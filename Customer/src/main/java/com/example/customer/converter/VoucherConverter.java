package com.example.customer.converter;

import com.example.customer.domain.Voucher;
import com.example.customer.entity.VoucherEntity;

public class VoucherConverter {
    public static Voucher toModel(VoucherEntity voucherEntity) {
        Voucher voucher = new Voucher();
        voucher.setId(voucherEntity.getId());
        voucher.setTitle(voucherEntity.getTitle());
        voucher.setCode(voucherEntity.getCode());
        voucher.setPercentage(voucherEntity.getPercentage());
        voucher.setUsageLimit(voucherEntity.getUsageLimit());
        voucher.setStartDate(voucherEntity.getStartDate());
        voucher.setEndDate(voucherEntity.getEndDate());
        voucher.setConditionPrice(voucherEntity.getConditionPrice());
        voucher.setExpired(voucherEntity.isExpired());
        voucher.setType(voucherEntity.getType());
        return voucher;
    }
}
