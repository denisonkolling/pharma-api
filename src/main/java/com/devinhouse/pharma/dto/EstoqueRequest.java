package com.devinhouse.pharma.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstoqueRequest {

    @NotNull(message = "Campo obrigatório")
    private Long cnpj;

    @NotNull(message = "Campo obrigatório")
    private Integer nroRegistro;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotNull(message = "Campo obrigatório")
    private Integer quantidade;

}
