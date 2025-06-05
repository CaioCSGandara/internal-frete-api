package com.internal.frete.api.service.strategy;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;

public class FreteEconomico implements CalculoFreteStrategy {
    public double calcular(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteValoresDefault) {
        return (calculoFreteRequest.getDistancia() * 0.3
                + calculoFreteRequest.getPeso() * 0.1
                + freteValoresDefault.getFreteEconomico().getTaxaFixa()) * (1 + freteValoresDefault.getFreteEconomico().getMargemDeLucro());
    }

    @Override
    public boolean isFreteAplicavel(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteValoresDefault) {
        return (calculoFreteRequest.getPeso() <= freteValoresDefault.getFreteEconomico().getPesoMaximoKg()
        && calculoFreteRequest.getDistancia() <= freteValoresDefault.getFreteEconomico().getDistanciaMaximaKm());
    }
}