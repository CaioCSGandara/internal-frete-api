package com.internal.frete.api.controller;

import com.internal.frete.api.dto.CalculoFreteRequest;
import com.internal.frete.api.service.FreteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreteControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void calcularFreteUnico200() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest(10, 30, "normal");

        ResponseEntity<String> response = testRestTemplate.postForEntity("/calcular-frete/", calculoFreteRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void calcularFreteUnico422() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest(102, 30, "normal");

        ResponseEntity<String> response = testRestTemplate.postForEntity("/calcular-frete/", calculoFreteRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    void calcularFreteUnico400() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest(60, -10, "normal");

        ResponseEntity<String> response = testRestTemplate.postForEntity("/calcular-frete/", calculoFreteRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    void calcularMultiplosFretes200() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest(10, 30, null);

        ResponseEntity<String> response = testRestTemplate.postForEntity("/calcular-frete/aplicaveis", calculoFreteRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void calcularMultiplosFretes422() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest(10, 3500, null);

        ResponseEntity<String> response = testRestTemplate.postForEntity("/calcular-frete/aplicaveis", calculoFreteRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    void calcularMultiplosFretes400() {
        CalculoFreteRequest calculoFreteRequest = new CalculoFreteRequest(-10, 350, null);

        ResponseEntity<String> response = testRestTemplate.postForEntity("/calcular-frete/aplicaveis", calculoFreteRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}