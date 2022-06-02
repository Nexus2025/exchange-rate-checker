Приложение обращается к сервису курсов валют, и отображает gif.
Если курс по отношению к USD за сегодня стал выше вчерашнего, то отдает рандомную отсюда https://giphy.com/search/rich
Если ниже - отсюда https://giphy.com/search/broke
Если курсы равны - отсюда https://giphy.com/search/nothing

Использованы технологии
- Java 8
- Spring Boot 2
- Feign
- JUnit/Mockito
- Gradle
- Docker

## Инструкция по запуску через Docker
Склонировать репозиторий    
    
    git clone https://github.com/Nexus2025/exchange-rate-checker.git
        
Перейти в корневую папку проекта "exchange_rate_checker" и собрать образ на основе Dockerfile
    
    docker build -t exchange-rate-checker .
    
Запустить контейнер

    docker run -it -p 8080:8080 exchange-rate-checker
    
После запуска, сервис доступен по endpoint

    GET /gif/rate-difference/usd?comparedCurrency={currencyCode}
    
CurrencyCode - код валюты. Список кодов валют - https://docs.openexchangerates.org/docs/supported-currencies. Пример реального запроса:
 
    http://localhost:8080/gif/rate-difference/usd?comparedCurrency=RUB

Приложение логирует в консоль даты, коды валют, курсы валют, тег и url адрес для скачивания gif