package com.internal.frete.api.config;

import com.internal.frete.api.model.Frete;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class FreteValoresDefault {

    private static FreteValoresDefault instancia;
    private List<String> fretesDisponiveis = List.of("normal", "economico", "expresso");
    private Frete freteNormal = new Frete(0.2, 10,  100, 10, 1500);
    private Frete freteEconomico = new Frete(0.1, 5,  20, 0, 500);
    private Frete freteExpresso = new Frete(0.3, 20,  50, 0, 3000);

    private FreteValoresDefault() {
    }

    public static synchronized FreteValoresDefault getInstancia() {
        if (instancia == null) {
            instancia = new FreteValoresDefault();
        }
        return instancia;
    }
}