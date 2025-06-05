package com.internal.frete.api.service.strategy;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;

public class FreteExpresso implements CalculoFreteStrategy {
    public double calcular(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteValoresDefault) {
        return (calculoFreteRequest.getDistancia() * 0.8 +
                calculoFreteRequest.getPeso() * 0.4
                + freteValoresDefault.getFreteExpresso().getTaxaFixa()) * (1 + freteValoresDefault.getFreteExpresso().getMargemDeLucro());
    }

    @Override
    public boolean isFreteAplicavel(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteValoresDefault) {
        return (calculoFreteRequest.getPeso() <= freteValoresDefault.getFreteExpresso().getPesoMaximoKg()
                && calculoFreteRequest.getDistancia() <= freteValoresDefault.getFreteExpresso().getDistanciaMaximaKm());
    }
}