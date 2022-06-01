package com.exchange_rate_checker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GifDto {
    private Data data;

    @JsonIgnore
    private String url;

    public GifDto() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public class Data {
        private Image images;

        public Data() {
        }

        public Image getImages() {
            return images;
        }

        public void setImages(Image images) {
            this.images = images;
        }


        public class Image {
            private Map<String, String> original;

            public Image() {
            }

            public Map<String, String> getOriginal() {
                return original;
            }

            public void setOriginal(Map<String, String> original) {
                this.original = original;

                url = original.get("url");
            }
        }
    }
}
