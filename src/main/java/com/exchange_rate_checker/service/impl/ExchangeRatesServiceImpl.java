package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.client.OpenExchangeRatesClient;
import com.exchange_rate_checker.dto.ExchangeRatesDto;
import com.exchange_rate_checker.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private OpenExchangeRatesClient openExchangeRatesClient;

    @Autowired
    public ExchangeRatesServiceImpl(OpenExchangeRatesClient openExchangeRatesClient) {
        this.openExchangeRatesClient = openExchangeRatesClient;
    }

    @Override
    public ExchangeRatesDto getLatestRates(String baseCurrency) {
        return openExchangeRatesClient.getLatestRates(baseCurrency);
    }

    @Override
    public ExchangeRatesDto getRatesByDate(String date, String baseCurrency) {
        return openExchangeRatesClient.getRatesByDate(date, baseCurrency);
    }
}