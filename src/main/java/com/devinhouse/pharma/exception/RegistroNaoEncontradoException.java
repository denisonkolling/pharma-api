package com.devinhouse.pharma.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RegistroNaoEncontradoException extends RuntimeException{

    private String nome;

    private String id;


    public RegistroNaoEncontradoException(String nome, Long id) {
        this(nome, id.toString());
    }


    public String getMessage() {
        if (nome == null || id == null)
            return null;
        return String.format("Não foi encontrado registro '%s' com número '%s'", nome, id);
    }

}
