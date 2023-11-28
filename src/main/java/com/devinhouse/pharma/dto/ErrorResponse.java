package com.devinhouse.pharma.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String titulo;

    private String mensagem;

    public ErrorResponse(String titulo, String mensage) {
        this.titulo = titulo;
        this.mensagem = mensage;
    }
}
