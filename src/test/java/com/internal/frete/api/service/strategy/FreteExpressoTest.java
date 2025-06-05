package com.internal.frete.api.service.strategy;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FreteExpressoTest {

    @Test
    public void calcularFreteComEntradaValida() {
        CalculoFreteStrategy freteExpresso = new FreteExpresso();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("expresso");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(100);

        double resultado = freteExpresso.calcular(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertEquals(135.2, resultado, 0.01);
    }

    @Test
    public void verificaFreteAplicavelComValoresValidos() {

        CalculoFreteStrategy freteExpresso = new FreteExpresso();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("expresso");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(100);

        boolean isFreteAplicavel = freteExpresso.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertTrue(isFreteAplicavel);
    }

    @Test
    public void verificaTesteAplicavelComPesoAcimaDoLimite() {
        CalculoFreteStrategy freteExpresso = new FreteExpresso();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("expresso");
        calculoFreteRequest.setPeso(51);
        calculoFreteRequest.setDistancia(100);

        boolean isFreteAplicavel = freteExpresso.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertFalse(isFreteAplicavel);
    }

    @Test
    public void verificaTesteAplicavelComDistanciaAcimaDoLimite() {
        CalculoFreteStrategy freteExpresso = new FreteExpresso();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("expresso");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(3001);

        boolean isFreteAplicavel = freteExpresso.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertFalse(isFreteAplicavel);
    }
}
