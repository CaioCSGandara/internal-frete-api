package com.internal.frete.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculoFreteResponse {
    private String tipoFrete;
    private double valorFrete;
    private String errorMsg;
}