package com.internal.frete.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Frete {
    private double margemDeLucro;
    private double taxaFixa;
    private double pesoMaximoKg;
    private double distanciaMinimaKm;
    private double distanciaMaximaKm;
}