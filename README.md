# API interna para cálculo de frete
A ideia deste web app é simular a API interna de uma empresa que vende máquinas hidráulicas para cálculo de fretes. Com ela, é possível:
- Calcular um frete específico entre as opções `normal, expresso e economico`
- Calcular todos os fretes disponíveis de uma só vez

<br>

# Requisitos
- Instalar Maven 4.9.9 
- Instalar Java JDK 21

<br>

# Instruções 
Após a instalação, podemos exploraro projeto com os seguintes comandos:

<br>

- Compilar dependências do projeto (necessário logo após a instalação)
  - `mvn compile`
  
<br>
 
- Rodar testes
  - `mvn test`
 
<br>

- Rodar app (porta 8080 por default)
  - `mvn spring-boot:run`

<br>

# Regras para cálculo de frete
`Frete Normal:`
- 0 < peso < 101
- 9 < distancia < 1501

  <br>

`Frete Expresso:`
- 0 < peso < 51
- 0 < distancia < 3001

<br>

`Frete Econômico`
- 0 < peso < 21
- 0 < distancia < 501


<br>

# Rotas

<br>

### 🟡 POST /calcular-frete/
Essa rota é reponsável por calcular e devolver o valor de um frete específico. Caso o frete não seja aplicável para os valores enviados, a resposta será uma mensagem de erro.

Parâmetros:
- peso: peso em kg do pacote a ser transportado (valor deve estar entre 1 e 1000)
- distancia: distância em km a ser percorrida (valor deve estar entre 5000)
- tipoFrete: tipo do frete que deseja calcular <b>entre as opções disponíveis</b>: 'normal', 'expresso', ou 'economico'

<br>

Exemplo de body válido:
```json
{
    "peso": "10",
    "distancia": "300",
    "tipoFrete": "normal"
}
```

Resposta esperada (HTTP 200 - OK):
```json
{
    "data": {
        "tipoFrete": "normal",
        "valorFrete": 194.4
    },
    "error": null
}
```

<details>

<summary>Erros possíveis</summary>

<br>

Resposta esperada para valor de peso ou distância não aplicável para o frete escolhido (HTTP 422 - UNPROCESSABLE ENTITY):
```json
{
    "data": null,
    "error": "Tipo de frete não para peso e/ou localização fornecidos."
}
```

<br>

Resposta esperada para valor de tipoFrete não existente (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Tipo de frete escolhido não existe."
}
```

<br>

Resposta esperada para valor de peso igual ou menor a 0 (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Peso não pode ser 0 ou negativo."
}
```

<br>

Resposta esperada para valor de peso acima de 1000kg (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Peso não pode exceder 1 tonelada."
}
```

<br>


Resposta esperada para valor de distância acima de 5000km (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Distância não pode exceder 5000km km."
}
```

<br>

Resposta esperada para valor de distância menor ou igual a 0 (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Distância não pode ser 0 ou negativa."
}
```

</details>

<br>

---

### 🟡 POST /calcular-frete/aplicaveis
Rota resposável por calcular e retornar todos os fretes que forem aplicáveis para os valores de entrada fornecidos pelo usuário

Parâmetros:
- peso: peso em kg do pacote a ser transportado (valor deve estar entre 1 e 1000)
- distancia: distância em km a ser percorrida (valor deve estar entre 5000)
- tipoFrete: null (a API já sabe todos os tipos de frete que existem)

<br>


Exemplo de body válido:
```json
{
    "peso": "10",
    "distancia": "200",
    "tipoFrete": "null"
}
```

Resposta esperada (HTTP 200 - OK):
```json
{
    "data": [
        {
            "tipoFrete": "normal",
            "valorFrete": 134.4
        },
        {
            "tipoFrete": "economico",
            "valorFrete": 72.60000000000001
        },
        {
            "tipoFrete": "expresso",
            "valorFrete": 239.20000000000002
        }
    ],
    "error": null
}
```

<details>

<summary>Erros possíveis</summary>

<br>

Resposta esperada quando não existe nenhum frete aplicável para os valores de entrada:
```json
{
    "data": null,
    "error": "Nenhum frete está disponível para peso e/ou localização fornecidos."
}
```

Resposta esperada para valor de peso igual ou menor a 0 (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Peso não pode ser 0 ou negativo."
}
```

<br>

Resposta esperada para valor de peso acima de 1000kg (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Peso não pode exceder 1 tonelada."
}
```

<br>


Resposta esperada para valor de distância acima de 5000km (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Distância não pode exceder 5000km km."
}
```

<br>

Resposta esperada para valor de distância menor ou igual a 0 (HTTP 400 - BAD REQUEST):
```json
{
    "data": null,
    "error": "Distância não pode ser 0 ou negativa."
}
```

</details>


  





  
