package com.exchange_rate_checker.service;

import com.exchange_rate_checker.dto.GifDto;

public interface GifService {

    GifDto getRandomGifByTag(String tag);
}