package com.example.admin.Service;

import com.example.admin.Domain.Voucher;
import com.example.admin.Domain.VoucherDTO;
import com.example.admin.enums.VoucherType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {
//    List<Voucher> getAllVoucherByConditions();
//
    List<Voucher> getAllVoucher();
    boolean addVoucher(VoucherDTO voucher);

    Voucher getVoucherById(Long voucherId);

    void updateVoucher(VoucherDTO voucher);

//    VoucherType[] getAllVoucherType();
}
