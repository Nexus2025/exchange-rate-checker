package com.exchange_rate_checker.data;

import com.exchange_rate_checker.dto.ExchangeRatesDto;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRatesDtoData {

    public static ExchangeRatesDto getExchangeRatesDtoTodayMore() {
        ExchangeRatesDto expected = new ExchangeRatesDto();
        expected.setBase("USD");
        expected.setDisclaimer("TEST");
        expected.setLicense("TEST");
        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 65.5);

        expected.setRates(rates);
        expected.setTimestamp(123456789L);

        return expected;
    }

    public static ExchangeRatesDto getExchangeRatesDtoYesterdayLess() {
        ExchangeRatesDto expected = new ExchangeRatesDto();
        expected.setBase("USD");
        expected.setDisclaimer("TEST");
        expected.setLicense("TEST");
        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 60.5);

        expected.setRates(rates);
        expected.setTimestamp(123456789L);

        return expected;
    }

    public static ExchangeRatesDto getExchangeRatesDtoEqual() {
        ExchangeRatesDto expected = new ExchangeRatesDto();
        expected.setBase("USD");
        expected.setDisclaimer("TEST");
        expected.setLicense("TEST");
        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 60.5);

        expected.setRates(rates);
        expected.setTimestamp(123456789L);

        return expected;
    }

    public static ExchangeRatesDto getExchangeRatesDtoTodayLess() {
        ExchangeRatesDto expected = new ExchangeRatesDto();
        expected.setBase("USD");
        expected.setDisclaimer("TEST");
        expected.setLicense("TEST");
        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 60.5);

        expected.setRates(rates);
        expected.setTimestamp(123456789L);

        return expected;
    }

    public static ExchangeRatesDto getExchangeRatesDtoYesterdayMore() {
        ExchangeRatesDto expected = new ExchangeRatesDto();
        expected.setBase("USD");
        expected.setDisclaimer("TEST");
        expected.setLicense("TEST");
        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 65.5);

        expected.setRates(rates);
        expected.setTimestamp(123456789L);

        return expected;
    }

    public static ExchangeRatesDto getExpectedExchangeRates() {
        ExchangeRatesDto expected = new ExchangeRatesDto();
        expected.setBase("TEST");
        expected.setDisclaimer("TEST");
        expected.setLicense("TEST");
        expected.setRates(new HashMap<>());
        expected.setTimestamp(123456789L);

        return expected;
    }
}
