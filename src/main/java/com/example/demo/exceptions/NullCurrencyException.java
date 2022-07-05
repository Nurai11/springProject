package com.example.demo.exceptions;

public class NullCurrencyException extends RuntimeException {
    public NullCurrencyException(String currency_is_null) {
        super(currency_is_null);
    }
}
