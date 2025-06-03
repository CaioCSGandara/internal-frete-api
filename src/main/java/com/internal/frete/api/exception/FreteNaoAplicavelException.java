package com.internal.frete.api.exception;

public class FreteNaoAplicavelException extends RuntimeException {
    public FreteNaoAplicavelException(String message) {
        super(message);
    }
}