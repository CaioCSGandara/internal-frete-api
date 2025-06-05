package com.internal.frete.api.service.strategy;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;

public interface CalculoFreteStrategy {
    double calcular(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteConfig);
    boolean isFreteAplicavel(CalculoFreteRequest calculoFreteRequest, FreteValoresDefault freteValoresDefault);
}