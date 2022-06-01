package com.exchange_rate_checker.client;

import com.exchange_rate_checker.dto.GifDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${giphy.name}", url = "${giphy.url}")
public interface GiphyClient {

    @GetMapping("${giphy.endpoint.random}")
    ResponseEntity<GifDto> getRandomGifByTag(@PathVariable("tag") String tag);
}