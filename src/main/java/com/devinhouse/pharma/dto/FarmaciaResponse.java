package com.devinhouse.pharma.dto;

import com.devinhouse.pharma.model.Endereco;
import lombok.Data;

@Data
public class FarmaciaResponse {

    private Long cnpj;

    private String razaoSocial;

    private String nomeFantasia;

    private String email;

    private String telefone;

    private String celular;

    private Endereco endereco;
}
