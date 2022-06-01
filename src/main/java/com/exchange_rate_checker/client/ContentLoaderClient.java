package com.exchange_rate_checker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "content-loader", url = "placeholder")
public interface ContentLoaderClient {

    @GetMapping
    ResponseEntity<byte[]> loadContentByUrl(URI url);
}