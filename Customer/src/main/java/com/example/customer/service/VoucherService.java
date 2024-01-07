package com.example.customer.service;

import com.example.customer.domain.Voucher;

import java.util.List;

public interface VoucherService {
    List<Voucher> getAllVoucherByConditions();
}
