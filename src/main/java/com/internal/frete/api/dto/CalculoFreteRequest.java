package com.internal.frete.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoFreteRequest implements Cloneable {

    @Min(value = 1, message = "Peso não pode ser 0 ou negativo.")
    @Max(value = 1000, message = "Peso não pode exceder 1 tonelada.")
    private double peso;

    @Min(value = 1, message = "Distância não pode ser 0 ou negativa.")
    @Max(value = 5000, message = "Distância não pode exceder 5000km km.")
    private double distancia;
    private String tipoFrete;


    public CalculoFreteRequest(CalculoFreteRequest modelo) {
        this.peso = modelo.getPeso();
        this.distancia = modelo.getDistancia();
        this.tipoFrete = modelo.getTipoFrete();
    }

    @Override
    public Object clone() {
        CalculoFreteRequest copia = null;
        try {
            copia = new CalculoFreteRequest(this);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return copia;
    }
}