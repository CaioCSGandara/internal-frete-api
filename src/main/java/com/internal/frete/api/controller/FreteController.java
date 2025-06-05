package com.internal.frete.api.controller;

import com.internal.frete.api.dto.CalculoFreteRequest;
import com.internal.frete.api.dto.CalculoFreteResponse;
import com.internal.frete.api.dto.ResponsePayload;
import com.internal.frete.api.exception.FreteNaoAplicavelException;
import com.internal.frete.api.service.FreteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calcular-frete")
@AllArgsConstructor
public class FreteController {

    private FreteService freteService;

    @PostMapping("/")
    public ResponseEntity<ResponsePayload> calcularFrete(@Valid @RequestBody CalculoFreteRequest request, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult.getAllErrors().get(0).getDefaultMessage());

        CalculoFreteResponse calculoFreteResponse = freteService.calcularFreteUnico(request);

        if(calculoFreteResponse==null) throw new FreteNaoAplicavelException("Tipo de frete não para peso e/ou localização fornecidos.");

        ResponsePayload responsePayload = new ResponsePayload(calculoFreteResponse, null);

        return ResponseEntity.ok().body(responsePayload);
    }


    @PostMapping("/aplicaveis")
    public ResponseEntity<ResponsePayload> fretesAplicaveis(@Valid @RequestBody CalculoFreteRequest request, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult.getAllErrors().get(0).getDefaultMessage());

        List<CalculoFreteResponse> fretesAplicaveis = freteService.calcularFretesAplicaveis(request);

        if(fretesAplicaveis.isEmpty()) throw new FreteNaoAplicavelException("Nenhum frete está disponível para peso e/ou localização fornecidos.");

        return ResponseEntity.ok().body(new ResponsePayload(fretesAplicaveis, null));
    }

}