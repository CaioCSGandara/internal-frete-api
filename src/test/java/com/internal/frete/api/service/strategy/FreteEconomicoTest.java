package com.internal.frete.api.service.strategy;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FreteEconomicoTest {

    @Test
    public void calcularFreteComEntradaValida() {
        CalculoFreteStrategy freteEconomico = new FreteEconomico();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("economico");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(100);

        double resultado = freteEconomico.calcular(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertEquals(39.6, resultado);
    }

    @Test
    public void verificaFreteAplicavelComValoresValidos() {

        CalculoFreteStrategy freteEconomico = new FreteEconomico();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("economico");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(100);

        boolean isFreteAplicavel = freteEconomico.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertTrue(isFreteAplicavel);
    }

    @Test
    public void verificaTesteAplicavelComPesoAcimaDoLimite() {
        CalculoFreteStrategy freteEconomico = new FreteEconomico();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("economico");
        calculoFreteRequest.setPeso(21);
        calculoFreteRequest.setDistancia(100);

        boolean isFreteAplicavel = freteEconomico.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertFalse(isFreteAplicavel);
    }

    @Test
    public void verificaTesteAplicavelComDistanciaAcimaDoLimite() {
        CalculoFreteStrategy freteEconomico = new FreteEconomico();

        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setTipoFrete("economico");
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(501);

        boolean isFreteAplicavel = freteEconomico.isFreteAplicavel(calculoFreteRequest, FreteValoresDefault.getInstancia());

        assertFalse(isFreteAplicavel);
    }
}
