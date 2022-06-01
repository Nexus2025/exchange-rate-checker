package com.exchange_rate_checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeRateCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateCheckerApplication.class, args);
    }

}
