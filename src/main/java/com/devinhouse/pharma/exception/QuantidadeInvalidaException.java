package com.devinhouse.pharma.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class QuantidadeInvalidaException extends RuntimeException{


    private String nome;

    private String quantidade;


    public QuantidadeInvalidaException(String nome, Long quantidade) {
        this(nome, quantidade.toString());
    }

    public QuantidadeInvalidaException(String nome, Integer quantidade) {
        this(nome, quantidade.toString());
    }


    public String getMessage() {
        if (nome == null || quantidade == null)
            return null;
        return String.format("Produto '%s' quantidade '%s' inválida", nome, quantidade);
    }

}

