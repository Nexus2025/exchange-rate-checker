package com.exchange_rate_checker.controller;

import com.exchange_rate_checker.exception.InvalidCurrencyCodeException;
import com.exchange_rate_checker.service.impl.GifGiverByRatioDifferenceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(GifRateDifferenceController.class)
class GifRateDifferenceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GifGiverByRatioDifferenceImpl gifGiverByRatioDifference;

    @Test
    public void getGif_Should_Return_Gif() throws Exception {
        byte[] mockGif= new byte[20];
        new Random().nextBytes(mockGif);

        BDDMockito.given(this.gifGiverByRatioDifference
                .compareTodayAndTomorrowRatioDifferenceAndGetGif(anyString(), anyString()))
                .willReturn(ResponseEntity.ok(mockGif));

        this.mvc.perform(get("/gif/rate-difference/usd?comparedCurrency=RUB")
                .accept(MediaType.IMAGE_GIF_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().bytes(mockGif));
    }

    @Test
    public void getGif_Should_Return_NOT_FOUND() throws Exception {
        BDDMockito.given(this.gifGiverByRatioDifference
                .compareTodayAndTomorrowRatioDifferenceAndGetGif(anyString(), anyString()))
                .willThrow(new InvalidCurrencyCodeException("Currency code= XXX does not exist"));

        this.mvc.perform(get("/gif/rate-difference/usd?comparedCurrency=XXX"))
                .andExpect(status().isNotFound());
    }
}