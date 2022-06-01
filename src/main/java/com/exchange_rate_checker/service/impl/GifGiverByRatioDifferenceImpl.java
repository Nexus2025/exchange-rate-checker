package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.dto.ExchangeRatesDto;
import com.exchange_rate_checker.dto.GifDto;
import com.exchange_rate_checker.exception.InvalidCurrencyCodeException;
import com.exchange_rate_checker.service.ContentLoaderService;
import com.exchange_rate_checker.service.ExchangeRatesService;
import com.exchange_rate_checker.service.GifGiverByRatioDifference;
import com.exchange_rate_checker.service.GifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class GifGiverByRatioDifferenceImpl implements GifGiverByRatioDifference {

    private ExchangeRatesService exchangeRatesService;
    private GifService gifService;
    private ContentLoaderService contentLoaderService;

    @Value("${giphy.broke}")
    private String less;

    @Value("${giphy.rich}")
    private String greater;

    @Value("${giphy.nothing}")
    private String equal;

    private static Logger log = LoggerFactory.getLogger(GifGiverByRatioDifferenceImpl.class);

    @Autowired
    public GifGiverByRatioDifferenceImpl(ExchangeRatesService exchangeRatesService, GifService gifService,
                                         ContentLoaderService contentLoaderService) {
        this.exchangeRatesService = exchangeRatesService;
        this.gifService = gifService;
        this.contentLoaderService = contentLoaderService;
    }

    @Override
    public ResponseEntity<byte[]> compareTodayAndTomorrowRatioDifferenceAndGetGif(String baseCurrency,
                                                                                  String comparedCurrency) {

        int comparisonResult = compareTodayAndTomorrowRatio(baseCurrency, comparedCurrency);
        String tag = getTagByComparisonResult(comparisonResult);
        return getRandomGifByTag(tag);
    }

    private int compareTodayAndTomorrowRatio(String baseCurrency, String comparedCurrency) {
        ExchangeRatesDto todayRate = exchangeRatesService.getLatestRates(baseCurrency);
        LocalDate currentDate = LocalDateTime.ofEpochSecond(todayRate.getTimestamp(), 0, ZoneOffset.UTC).toLocalDate();

        ExchangeRatesDto yesterdayRate = exchangeRatesService.getRatesByDate(currentDate.minusDays(1).toString(), baseCurrency);
        LocalDate yesterdayDate = LocalDateTime.ofEpochSecond(yesterdayRate.getTimestamp(), 0, ZoneOffset.UTC).toLocalDate();

        if (yesterdayRate.getRatio(comparedCurrency) == null) {
            throw new InvalidCurrencyCodeException(String.format("Currency code= %s does not exist", comparedCurrency));
        }

        log.info("RATE BY {} | {}/{}: {}",
                yesterdayDate, baseCurrency, comparedCurrency, yesterdayRate.getRatio(comparedCurrency));
        log.info("RATE BY {} | {}/{}: {}",
                currentDate, baseCurrency, comparedCurrency, todayRate.getRatio(comparedCurrency));
        return todayRate.getRatio(comparedCurrency).compareTo(yesterdayRate.getRatio(comparedCurrency));
    }

    private String getTagByComparisonResult(int comparisonResult) {
        String tag;
        if (comparisonResult > 0) {
            tag = greater;
        } else if (comparisonResult < 0) {
            tag = less;
        } else {
            tag = equal;
        }

        log.info("TAG: {}", tag);
        return tag;
    }

    private ResponseEntity<byte[]> getRandomGifByTag(String tag) {
        GifDto gif = gifService.getRandomGifByTag(tag);

        log.info("GIF URL: {}", gif.getUrl());
        return contentLoaderService.loadContentByUrl(URI.create(gif.getUrl()));
    }
}