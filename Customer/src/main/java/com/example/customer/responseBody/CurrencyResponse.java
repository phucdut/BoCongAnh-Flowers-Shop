package com.example.customer.responseBody;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyResponse {
    private String result;
    private Map<String, Double> rates;
    // Lấy giá trị chuyển đổi VND
    public Double getVNDExchangeRate() {
        if (rates != null && rates.containsKey("VND")) {
            return rates.get("VND");
        } else {
            return null;
        }
    }
}
