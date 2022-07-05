package com.example.demo.controller;

import com.example.demo.model.CurrencyModel;
import com.example.demo.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping(path = "/create")
    public ResponseEntity<CurrencyModel> createNewCurrency(@RequestBody CurrencyModel currencyModel) {
        CurrencyModel model = currencyService.createNewCurrency(currencyModel);
        if (model.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(model);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateCurrency(@RequestBody CurrencyModel currencyModel) {
        try {
            currencyService.update(currencyModel);
            return ResponseEntity.ok(true);
        } catch (RuntimeException exception) {
            log.error(exception.getMessage(), exception);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<CurrencyModel> getCurrencyById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(currencyService.getById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping(path = "/getAllCurrencies")
    public ResponseEntity<List<CurrencyModel>> getAllCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }
}
