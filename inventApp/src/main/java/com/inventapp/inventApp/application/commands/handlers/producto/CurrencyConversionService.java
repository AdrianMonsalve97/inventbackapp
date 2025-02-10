package com.inventapp.inventApp.application.commands.handlers.producto;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConversionService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public double convertirMoneda(String monedaOrigen, double cantidad, String monedaDestino) {
        if (monedaOrigen.equalsIgnoreCase(monedaDestino)) {
            return cantidad;
        }

        String url = API_URL + monedaOrigen;
        RestTemplate restTemplate = new RestTemplate();
        CurrencyResponse response = restTemplate.getForObject(url, CurrencyResponse.class);

        if (response != null && response.getRates().containsKey(monedaDestino)) {
            return cantidad * response.getRates().get(monedaDestino);
        }

        throw new RuntimeException("No se pudo obtener la tasa de cambio para " + monedaDestino);
    }
}