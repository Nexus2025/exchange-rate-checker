package com.exchange_rate_checker.data;

import com.exchange_rate_checker.dto.GifDto;

public class GifDtoData {

    public static GifDto getGifDtoRich() {
        GifDto gifDto = new GifDto();
        GifDto.Data data = new GifDto().new Data();
        GifDto.Data.Image image = new GifDto().new Data().new Image();

        data.setImages(image);
        gifDto.setData(data);
        gifDto.setUrl("rich");

        return gifDto;
    }

    public static GifDto getGifDtoBroke() {
        GifDto gifDto = new GifDto();
        GifDto.Data data = new GifDto().new Data();
        GifDto.Data.Image image = new GifDto().new Data().new Image();

        data.setImages(image);
        gifDto.setData(data);
        gifDto.setUrl("broke");

        return gifDto;
    }

    public static GifDto getGifDtoNothing() {
        GifDto gifDto = new GifDto();
        GifDto.Data data = new GifDto().new Data();
        GifDto.Data.Image image = new GifDto().new Data().new Image();

        data.setImages(image);
        gifDto.setData(data);
        gifDto.setUrl("nothing");

        return gifDto;
    }

    public static GifDto getExpectedGifDto() {
        GifDto gifDto = new GifDto();
        GifDto.Data data = new GifDto().new Data();
        GifDto.Data.Image image = new GifDto().new Data().new Image();

        data.setImages(image);
        gifDto.setData(data);
        gifDto.setUrl("TEST");

        return gifDto;
    }
}
