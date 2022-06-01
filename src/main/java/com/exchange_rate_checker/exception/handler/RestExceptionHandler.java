package com.exchange_rate_checker.exception.handler;

import com.exchange_rate_checker.exception.InvalidCurrencyCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(InvalidCurrencyCodeException.class)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest req, InvalidCurrencyCodeException e) {
        ErrorInfo info = new ErrorInfo(req.getRequestURI(), e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
    }
}