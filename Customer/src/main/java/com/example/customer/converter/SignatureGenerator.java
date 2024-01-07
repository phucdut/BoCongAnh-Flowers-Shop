package com.example.customer.converter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.stream.Collectors;

public class SignatureGenerator {

    public static String generateSignature(Map<String, String> params, String secretKey) {
        String sortedParams = params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        return calculateHmacSHA256(sortedParams, secretKey);
    }

    private static String calculateHmacSHA256(String data, String key) {
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKey);
            byte[] encodedHash = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash); // Chuyển đổi kết quả thành dạng hex
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace(); // Xử lý lỗi
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

