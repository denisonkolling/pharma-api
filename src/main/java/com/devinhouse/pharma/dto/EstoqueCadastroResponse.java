package com.devinhouse.pharma.dto;

import lombok.Data;

@Data
public class EstoqueCadastroResponse {

    private Long cnpj;

    private Integer nroRegistro;

    private Integer quantidade;

}
