package com.exchange_rate_checker.service.impl;

import com.exchange_rate_checker.client.ContentLoaderClient;
import com.exchange_rate_checker.service.ContentLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class ContentLoaderServiceImpl implements ContentLoaderService {

    private ContentLoaderClient contentLoaderClient;

    @Autowired
    public ContentLoaderServiceImpl(ContentLoaderClient contentLoaderClient) {
        this.contentLoaderClient = contentLoaderClient;
    }

    @Override
    public ResponseEntity<byte[]> loadContentByUrl(URI url) {
        return contentLoaderClient.loadContentByUrl(url);
    }
}