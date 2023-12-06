package com.devinhouse.pharma.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueConsultaResponse {

    private Integer nroRegistro;

    private String nome;

    private Integer quantidade;

    private LocalDateTime dataAtualizacao;

}

