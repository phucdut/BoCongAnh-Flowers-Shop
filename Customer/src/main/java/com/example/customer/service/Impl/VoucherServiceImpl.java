package com.example.customer.service.Impl;

import com.example.customer.converter.VoucherConverter;
import com.example.customer.domain.Voucher;
import com.example.customer.entity.VoucherEntity;
import com.example.customer.repository.VoucherRepository;
import com.example.customer.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<Voucher> getAllVoucherByConditions() {
        updateExpiredVoucherByDate();
        return voucherRepository.findAllByExpiredFalse().stream().map(VoucherConverter::toModel).toList();
    }

    private void updateExpiredVoucherByDate() {
        List<VoucherEntity> voucherEntities = voucherRepository.findAllByExpiredFalse();
        for (VoucherEntity voucherEntity: voucherEntities) {
            if (voucherEntity.getEndDate().isBefore(LocalDate.now()) || voucherEntity.getUsageLimit() == 0) {
                voucherEntity.setExpired(true);
                voucherRepository.save(voucherEntity);
            }
        }
    }

    private void updateExpiredVoucherByUseLimit(VoucherEntity voucherEntity) {
        if (voucherEntity.getUsageLimit() == 0) {
            voucherEntity.setExpired(true);
            voucherRepository.save(voucherEntity);
        }
    }
}
