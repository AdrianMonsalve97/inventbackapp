package com.inventapp.inventApp.application.commands.handlers.producto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyResponse {
    private String base;
    private Map<String, Double> rates;
}