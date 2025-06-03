package com.internal.frete.api.service;

import com.internal.frete.api.config.FreteValoresDefault;
import com.internal.frete.api.dto.CalculoFreteRequest;
import com.internal.frete.api.dto.CalculoFreteResponse;
import com.internal.frete.api.service.strategy.CalculoFreteStrategy;
import com.internal.frete.api.service.strategy.FreteEconomico;
import com.internal.frete.api.service.strategy.FreteExpresso;
import com.internal.frete.api.service.strategy.FreteNormal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreteService {

    public CalculoFreteResponse calcularFreteUnico(CalculoFreteRequest request) {

        CalculoFreteStrategy strategy = escolherStrategy(request.getTipoFrete());
        if(strategy == null) {
            throw new IllegalArgumentException("Tipo de frete escolhido não existe.");
        }
        FreteValoresDefault freteValoresDefault = FreteValoresDefault.getInstancia();
        if(!strategy.isFreteAplicavel(request, freteValoresDefault)) {
            return null;
        }

        double valorFrete = strategy.calcular(request, freteValoresDefault);

        return new CalculoFreteResponse(request.getTipoFrete(), valorFrete, null);

    }

    public List<CalculoFreteResponse> calcularFretesAplicaveis(CalculoFreteRequest request) {

        List<String> fretesDisponiveis = FreteValoresDefault.getInstancia().getFretesDisponiveis();

        List<CalculoFreteResponse> fretes = new ArrayList<>();

        fretesDisponiveis.forEach(freteDisponivel -> {
            CalculoFreteRequest requestComTipoFrete = (CalculoFreteRequest) request.clone();
            requestComTipoFrete.setTipoFrete(freteDisponivel);

            CalculoFreteResponse freteCalculado = calcularFreteUnico(requestComTipoFrete);

            if(freteCalculado != null) fretes.add(freteCalculado);
        });

        return fretes;
    }


    private CalculoFreteStrategy escolherStrategy(String tipoFrete) {
        return switch (tipoFrete.toLowerCase()) {
            case "economico" -> new FreteEconomico();
            case "expresso"  -> new FreteExpresso();
            case "normal" -> new FreteNormal();
            default -> null;
        };
    }
}