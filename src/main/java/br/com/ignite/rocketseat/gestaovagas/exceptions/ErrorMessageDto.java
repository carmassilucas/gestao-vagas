package br.com.ignite.rocketseat.gestaovagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class ErrorMessageDto {
    private String message;
    private String field;
}
