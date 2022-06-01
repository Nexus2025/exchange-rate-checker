package com.exchange_rate_checker.controller;

import com.exchange_rate_checker.service.impl.GifGiverByRatioDifferenceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gif/rate-difference")
public class GifRateDifferenceController {

    private GifGiverByRatioDifferenceImpl gifGiverByRatioDifference;

    @Value("${exchangerates.base.currency.usd}")
    private String baseCurrency;

    @Autowired
    public GifRateDifferenceController(GifGiverByRatioDifferenceImpl gifGiverByRatioDifference) {
        this.gifGiverByRatioDifference = gifGiverByRatioDifference;
    }

    @GetMapping(value = "/usd", produces = "image/gif")
    public ResponseEntity<byte[]> getGif(@RequestParam String comparedCurrency) {
        return gifGiverByRatioDifference.compareTodayAndTomorrowRatioDifferenceAndGetGif(baseCurrency, comparedCurrency);
    }
}