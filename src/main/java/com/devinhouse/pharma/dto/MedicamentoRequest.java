package com.devinhouse.pharma.dto;

import com.devinhouse.pharma.model.TipoMedicamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicamentoRequest {

    @NotNull(message = "Campo obrigatório")
    private Integer nroRegistro;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    private String laboratorio;

    @NotBlank(message = "Campo obrigatório")
    private String dosagem;

    private String descricao;

    @NotNull(message = "Campo obrigatório")
    private Float preco;

    @NotNull(message = "Campo obrigatório")
    private TipoMedicamento tipo;
}
