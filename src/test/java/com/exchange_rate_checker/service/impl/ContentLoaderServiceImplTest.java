package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.client.ContentLoaderClient;
import com.exchange_rate_checker.service.ContentLoaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ContentLoaderServiceImplTest {

    @MockBean
    private ContentLoaderClient contentLoaderClient;

    @Autowired
    private ContentLoaderService contentLoaderService;

    @Test
    public void loadContentByUrl_Should_Return_ResponseEntity() {
        byte[] expected = new byte[20];
        new Random().nextBytes(expected);

        Mockito.when(this.contentLoaderClient.loadContentByUrl(any())).thenReturn(ResponseEntity.ok(expected));

        ResponseEntity<byte[]> actual = contentLoaderService.loadContentByUrl(URI.create("url"));
        Assertions.assertArrayEquals(expected, actual.getBody());
    }
}