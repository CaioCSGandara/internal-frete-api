package com.internal.frete.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponsePayload {
    Object data;
    String error;
}