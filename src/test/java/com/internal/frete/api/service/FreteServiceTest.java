package com.internal.frete.api.service;

import com.internal.frete.api.dto.CalculoFreteRequest;
import com.internal.frete.api.dto.CalculoFreteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FreteServiceTest {

    @Autowired
    private FreteService freteService;

    @Test
    void calculaFreteUnicoAplicavel() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setPeso(10);
        calculoFreteRequest.setDistancia(12);
        calculoFreteRequest.setTipoFrete("normal");

        CalculoFreteResponse calculoFreteResponse = freteService.calcularFreteUnico(calculoFreteRequest);

        assertThat(calculoFreteResponse.getTipoFrete()).isEqualTo("normal");
        assertEquals(21.59, calculoFreteResponse.getValorFrete(), 0.01);
    }

    @Test
    void naoCalculaFreteUnicoNaoAplicavelComPesoAcimaDoLimite() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setPeso(101);
        calculoFreteRequest.setDistancia(12);
        calculoFreteRequest.setTipoFrete("normal");

        CalculoFreteResponse calculoFreteResponse = freteService.calcularFreteUnico(calculoFreteRequest);

        assertThat(calculoFreteResponse).isNull();
    }


    @Test
    void naoCalculaFreteComTipoFreteInvalido() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest();
        calculoFreteRequest.setPeso(101);
        calculoFreteRequest.setDistancia(12);
        calculoFreteRequest.setTipoFrete("tipoFreteInvalido");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            freteService.calcularFreteUnico(calculoFreteRequest);
        });

        assertThat(exception.getMessage()).isEqualTo("Tipo de frete escolhido não existe.");
    }


    @Test
    void calculaERetornaTresFretesValidos() {
        List<CalculoFreteResponse> calculoFreteResponses = freteService.calcularFretesAplicaveis(new CalculoFreteRequest(20, 300, null));
        assertThat(calculoFreteResponses.size()).isEqualTo(3);
    }

    @Test
    void calculaERetornaDoisFretesValidos() {
        List<CalculoFreteResponse> calculoFreteResponses = freteService.calcularFretesAplicaveis(new CalculoFreteRequest(30, 300, null));
        assertThat(calculoFreteResponses.size()).isEqualTo(2);
    }

    @Test
    void calculaERetornaUmFreteValido() {
        List<CalculoFreteResponse> calculoFreteResponses = freteService.calcularFretesAplicaveis(new CalculoFreteRequest(60, 300, null));
        assertThat(calculoFreteResponses.size()).isEqualTo(1);
    }

    @Test
    void retornaNuloComTodosFretesInvalidos() {
        List<CalculoFreteResponse> calculoFreteResponses = freteService.calcularFretesAplicaveis(new CalculoFreteRequest(60, 5000, null));
        assertThat(calculoFreteResponses.size()).isEqualTo(0);
    }



    //calcular multiplos fretes:

    //devolve 2 fretes
    //devolve 1 frete
    //devolve null (nenhum frete aplicável)
}