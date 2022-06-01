package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.client.GiphyClient;
import com.exchange_rate_checker.dto.GifDto;
import com.exchange_rate_checker.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GifServiceImpl implements GifService {

    private GiphyClient giphyClient;

    @Autowired
    public GifServiceImpl(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    @Override
    public GifDto getRandomGifByTag(String tag) {
        return giphyClient.getRandomGifByTag(tag).getBody();
    }
}