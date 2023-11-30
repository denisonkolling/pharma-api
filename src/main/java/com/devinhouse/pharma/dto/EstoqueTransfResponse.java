package com.devinhouse.pharma.dto;

import lombok.Data;

@Data
public class EstoqueTransfResponse {

    private Integer registro;

    private Long cnpjOrigem;

    private Integer quantidadeOrigem;

    private Long cnpjDestino;

    private Integer quantidadeDestino;


}
