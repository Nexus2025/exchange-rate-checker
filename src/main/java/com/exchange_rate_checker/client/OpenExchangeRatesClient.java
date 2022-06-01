package com.exchange_rate_checker.client;

import com.exchange_rate_checker.dto.ExchangeRatesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${exchangerates.name}", url = "${exchangerates.url}")
public interface OpenExchangeRatesClient {

    @GetMapping("${exchangerates.endpoint.latest}")
    ExchangeRatesDto getLatestRates(@PathVariable String baseCurrency);

    @GetMapping("${exchangerates.endpoint.historical}")
    ExchangeRatesDto getRatesByDate(@PathVariable String date, @PathVariable String baseCurrency);
}