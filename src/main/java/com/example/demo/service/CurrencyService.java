package com.example.demo.service;

import com.example.demo.model.CurrencyModel;

import java.util.List;

public interface CurrencyService {
    CurrencyModel createNewCurrency(CurrencyModel currencyModel);
    CurrencyModel getById(Long id);
    List<CurrencyModel> getAllCurrencies();
    boolean update(CurrencyModel currencyModel);

}
