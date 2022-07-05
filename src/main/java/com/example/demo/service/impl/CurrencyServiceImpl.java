package com.example.demo.service.impl;

import com.example.demo.entity.CurrencyEntity;
import com.example.demo.exceptions.CurrencyNotFoundException;
import com.example.demo.model.CurrencyModel;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public CurrencyModel createNewCurrency(CurrencyModel currencyModel) {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        //todo mapstruct
        currencyEntity.setName(currencyModel.getName());
        currencyEntity.setRate(currencyModel.getRate());
        currencyEntity = currencyRepository.save(currencyEntity);

        currencyModel.setId(currencyEntity.getId());
        return currencyModel;
    }

    @Override
    public CurrencyModel getById(Long id) {
        CurrencyEntity exist = currencyRepository.findById(id)
                .orElseThrow(() -> new CurrencyNotFoundException("0"));
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setId(exist.getId());
        currencyModel.setName(exist.getName());
        currencyModel.setRate(exist.getRate());
        return currencyModel;

        //что такое сваггер
        //как можно закинуть через сваггер(как запихнуть в header)
        //не следует инжектить репозиторий в контроллер, нужно сервис

    }

    @Override
    public boolean update(@Valid CurrencyModel currencyModel) {
        CurrencyEntity currencyEntity = currencyRepository.findById(currencyModel.getId())
                .orElseThrow(() -> new CurrencyNotFoundException("null"));
        currencyEntity.setName(currencyModel.getName());
        currencyEntity.setRate(currencyModel.getRate());

        currencyEntity = currencyRepository.save(currencyEntity);
        return currencyEntity.getId() != null;
    }

    @Override
    public List<CurrencyModel> getAllCurrencies() {
        List<CurrencyEntity> currencyEntityList = new ArrayList<>();
        List<CurrencyModel> currencyModelList = new ArrayList<>();
        currencyModelList.forEach(el -> {
            CurrencyModel currencyModel = new CurrencyModel();
            currencyModel.setId(el.getId());
            currencyModel.setName(el.getName());
            currencyModel.setRate(el.getRate());

            currencyModelList.add(currencyModel);
        });
        return currencyModelList;
    }

}
