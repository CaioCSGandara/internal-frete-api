package com.internal.frete.api.service.strategy;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;

public class FreteNormal implements CalculoFreteStrategy {

    @Override
    public double calcular(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteValoresDefault) {
        return (calculoFreteRequest.getDistancia() * 0.5
                + calculoFreteRequest.getPeso() * 0.2
                + freteValoresDefault.getFreteNormal().getTaxaFixa()) * (1 + freteValoresDefault.getFreteNormal().getMargemDeLucro());
    }

    @Override
    public boolean isFreteAplicavel(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteValoresDefault) {
        return(calculoFreteRequest.getPeso() <= freteValoresDefault.getFreteNormal().getPesoMaximoKg()
        && calculoFreteRequest.getDistancia() >= freteValoresDefault.getFreteNormal().getDistanciaMinimaKm()
        && calculoFreteRequest.getDistancia() <= freteValoresDefault.getFreteNormal().getDistanciaMaximaKm());
    }
}