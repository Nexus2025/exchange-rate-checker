package com.exchange_rate_checker.service;

import org.springframework.http.ResponseEntity;

public interface GifGiverByRatioDifference {

    ResponseEntity<byte[]> compareTodayAndTomorrowRatioDifferenceAndGetGif(String baseCurrency,
                                                                           String comparedCurrency);
}
