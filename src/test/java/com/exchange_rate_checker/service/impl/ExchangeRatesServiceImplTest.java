package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.client.OpenExchangeRatesClient;
import com.exchange_rate_checker.dto.ExchangeRatesDto;
import com.exchange_rate_checker.service.ExchangeRatesService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.exchange_rate_checker.data.ExchangeRatesDtoData.getExpectedExchangeRates;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class ExchangeRatesServiceImplTest {

    @MockBean
    private OpenExchangeRatesClient openExchangeRatesClient;

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Test
    public void getLatestRates_Should_Return_ExchangeRatesDto() {
        ExchangeRatesDto expected = getExpectedExchangeRates();

        Mockito.when(this.openExchangeRatesClient.getLatestRates(anyString()))
                .thenReturn(expected);

        ExchangeRatesDto actual = exchangeRatesService.getLatestRates("XXX");
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    public void getRatesByDate_Should_Return_ExchangeRatesDto() {
        ExchangeRatesDto expected = getExpectedExchangeRates();

        Mockito.when(this.openExchangeRatesClient.getRatesByDate(anyString(), anyString()))
                .thenReturn(expected);

        ExchangeRatesDto actual = exchangeRatesService.getRatesByDate("date", "code");
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}