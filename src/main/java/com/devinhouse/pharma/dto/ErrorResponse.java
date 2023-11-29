package com.devinhouse.pharma.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponse {

    private String titulo;

    private String mensagem;

    private Map<String, String> detalhes;

    public ErrorResponse(String titulo, String mensagem, Map detalhes) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public ErrorResponse(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public ErrorResponse(String titulo) {
        this(titulo, null);
    }

}
