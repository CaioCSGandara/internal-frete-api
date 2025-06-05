package com.internal.frete.api.service.strategy;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FreteNormalTest {

    @Test
    public void calcularFreteComEntradaValida() {
        CalculoFreteStrategy freteNormal = new FreteNormal();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("normal");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(100);

        double resultado = freteNormal.calcular(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertEquals(74.39, resultado, 0.01);
    }

    @Test
    public void verificaFreteAplicavelComValoresValidos() {

        CalculoFreteStrategy freteNormal = new FreteNormal();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("normal");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(100);

        boolean isFreteAplicavel = freteNormal.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertTrue(isFreteAplicavel);
    }

    @Test
    public void verificaTesteAplicavelComPesoAcimaDoLimite() {
        CalculoFreteStrategy freteNormal = new FreteNormal();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("normal");
        calculoFreteRequest.setPeso(101);
        calculoFreteRequest.setDistancia(100);

        boolean isFreteAplicavel = freteNormal.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertFalse(isFreteAplicavel);
    }

    @Test
    public void verificaTesteAplicavelComDistanciaAcimaDoLimite() {
        CalculoFreteStrategy freteNormal = new FreteNormal();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("normal");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(1501);

        boolean isFreteAplicavel = freteNormal.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertFalse(isFreteAplicavel);
    }

    @Test
    public void verificaTesteAplicavelComDistanciaMenorQueAMinima() {
        CalculoFreteStrategy freteNormal = new FreteNormal();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("normal");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(9);

        boolean isFreteAplicavel = freteNormal.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertFalse(isFreteAplicavel);
    }
}