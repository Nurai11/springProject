package com.example.demo.exceptions;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String s) {
        super(s);
    }
}
