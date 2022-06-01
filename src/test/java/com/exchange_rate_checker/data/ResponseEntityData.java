package com.exchange_rate_checker.data;

import org.springframework.http.ResponseEntity;

public class ResponseEntityData {
    public static ResponseEntity<byte[]> getExpectedResponseEntity() {
        byte[] mockGif = {1, 2, 3, 4, 5, 6, 7};
        return ResponseEntity.ok(mockGif);
    }
}
