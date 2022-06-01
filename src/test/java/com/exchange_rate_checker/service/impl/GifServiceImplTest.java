package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.client.GiphyClient;
import com.exchange_rate_checker.dto.GifDto;
import com.exchange_rate_checker.service.GifService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.exchange_rate_checker.data.GifDtoData.getExpectedGifDto;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class GifServiceImplTest {

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private GifService gifService;

    @Test
    public void getRandomGifByTag_Should_Return_GifDto() {
        GifDto expected = getExpectedGifDto();

        Mockito.when(this.giphyClient.getRandomGifByTag(anyString()))
                .thenReturn(ResponseEntity.of(Optional.of(expected)));

        GifDto actual = gifService.getRandomGifByTag("tag");
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}