package com.exchange_rate_checker.service;

import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface ContentLoaderService {

    ResponseEntity<byte[]> loadContentByUrl(URI url);
}