package com.devinhouse.pharma.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueCadastroResponse {

    private Long cnpj;

    private Integer nroRegistro;

    private Integer quantidade;

    private LocalDateTime dataAtualizacao;

}
