package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
public class CurrencyModel {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private double rate;

}
