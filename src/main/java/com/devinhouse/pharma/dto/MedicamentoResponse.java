package com.devinhouse.pharma.dto;

import com.devinhouse.pharma.model.TipoMedicamento;
import lombok.Data;

@Data
public class MedicamentoResponse {

    private Integer registro;

    private String nome;

    private String laboratorio;

    private String dosagem;

    private String descricao;

    private Float preco;

    private TipoMedicamento tipo;
}
