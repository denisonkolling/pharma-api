package com.devinhouse.pharma.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstoqueCadastroRequest {

    @NotNull(message = "Campo obrigatório")
    private Long cnpj;

    @NotNull(message = "Campo obrigatório")
    private Integer nroRegistro;


    @NotNull(message = "Campo obrigatório")
    private Integer quantidade;

}
