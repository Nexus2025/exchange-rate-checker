package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.exception.InvalidCurrencyCodeException;
import com.exchange_rate_checker.service.ContentLoaderService;
import com.exchange_rate_checker.service.ExchangeRatesService;
import com.exchange_rate_checker.service.GifGiverByRatioDifference;
import com.exchange_rate_checker.service.GifService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.exchange_rate_checker.data.ExchangeRatesDtoData.*;
import static com.exchange_rate_checker.data.GifDtoData.*;
import static com.exchange_rate_checker.data.ResponseEntityData.*;

@SpringBootTest
public class GifGiverByRatioDifferenceImplTest {

    @Autowired
    private GifGiverByRatioDifference gifGiverByRatioDifference;

    @MockBean
    private ExchangeRatesService exchangeRatesService;

    @MockBean
    private GifService gifService;

    @MockBean
    private ContentLoaderService contentLoaderService;

    @Test
    public void compareTodayAndTomorrowRatioDifferenceAndGetGif_Should_Return_Gif_Rich() {
        Mockito.when(this.exchangeRatesService.getLatestRates(anyString()))
                .thenReturn(getExchangeRatesDtoTodayMore());

        Mockito.when(this.exchangeRatesService.getRatesByDate(anyString(), anyString()))
                .thenReturn(getExchangeRatesDtoYesterdayLess());

        Mockito.when(this.gifService.getRandomGifByTag("rich"))
                .thenReturn(getGifDtoRich());

        ResponseEntity<byte[]> expected = getExpectedResponseEntity();
        Mockito.when(this.contentLoaderService.loadContentByUrl(URI.create("rich")))
                .thenReturn(expected);

        ResponseEntity<byte[]> actual = gifGiverByRatioDifference
                .compareTodayAndTomorrowRatioDifferenceAndGetGif("USD", "RUB");

        Assertions.assertThat(actual.getBody())
                .isEqualTo(expected.getBody());
    }

    @Test
    public void compareTodayAndTomorrowRatioDifferenceAndGetGif_Should_Return_Gif_Broke() {
        Mockito.when(this.exchangeRatesService.getLatestRates(anyString()))
                .thenReturn(getExchangeRatesDtoTodayLess());

        Mockito.when(this.exchangeRatesService.getRatesByDate(anyString(), anyString()))
                .thenReturn(getExchangeRatesDtoYesterdayMore());

        Mockito.when(this.gifService.getRandomGifByTag("broke"))
                .thenReturn(getGifDtoBroke());

        ResponseEntity<byte[]> expected = getExpectedResponseEntity();
        Mockito.when(this.contentLoaderService.loadContentByUrl(URI.create("broke")))
                .thenReturn(expected);

        ResponseEntity<byte[]> actual = gifGiverByRatioDifference
                .compareTodayAndTomorrowRatioDifferenceAndGetGif("USD", "RUB");

        Assertions.assertThat(actual.getBody())
                .isEqualTo(expected.getBody());
    }

    @Test
    public void compareTodayAndTomorrowRatioDifferenceAndGetGif_Should_Return_Gif_Nothing() {
        Mockito.when(this.exchangeRatesService.getLatestRates(anyString()))
                .thenReturn(getExchangeRatesDtoEqual());

        Mockito.when(this.exchangeRatesService.getRatesByDate(anyString(), anyString()))
                .thenReturn(getExchangeRatesDtoEqual());

        Mockito.when(this.gifService.getRandomGifByTag("nothing"))
                .thenReturn(getGifDtoNothing());

        ResponseEntity<byte[]> expected = getExpectedResponseEntity();
        Mockito.when(this.contentLoaderService.loadContentByUrl(URI.create("nothing")))
                .thenReturn(expected);

        ResponseEntity<byte[]> actual = gifGiverByRatioDifference
                .compareTodayAndTomorrowRatioDifferenceAndGetGif("USD", "RUB");

        Assertions.assertThat(actual.getBody())
                .isEqualTo(expected.getBody());
    }

    @Test
    public void compareTodayAndTomorrowRatioDifferenceAndGetGif_Should_ThrowException() {
        Mockito.when(this.exchangeRatesService.getLatestRates(anyString()))
                .thenReturn(getExchangeRatesDtoEqual());

        Mockito.when(this.exchangeRatesService.getRatesByDate(anyString(), anyString()))
                .thenReturn(getExchangeRatesDtoEqual());

        InvalidCurrencyCodeException expected = assertThrows(InvalidCurrencyCodeException.class,
                () -> gifGiverByRatioDifference.compareTodayAndTomorrowRatioDifferenceAndGetGif("USD", "XXX"));

        assertTrue(expected.getMessage().contains("Currency code= XXX does not exist"));
    }
}