package com.internal.frete.api.exception;

import com.internal.frete.api.dto.ResponsePayload;
import com.internal.frete.api.model.Frete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    private ResponseEntity<ResponsePayload> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        ResponsePayload payload = new ResponsePayload(null, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }

    @ExceptionHandler(value = {FreteNaoAplicavelException.class})
    private ResponseEntity<ResponsePayload> freteNaoAplicavelExceptionHandler(FreteNaoAplicavelException exception) {
        ResponsePayload payload = new ResponsePayload(null, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(payload);
    }
}