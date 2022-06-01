package com.exchange_rate_checker.service;

import com.exchange_rate_checker.dto.ExchangeRatesDto;

public interface ExchangeRatesService {

    ExchangeRatesDto getLatestRates(String baseCurrencyCode);

    ExchangeRatesDto getRatesByDate(String date, String baseCurrency);
}