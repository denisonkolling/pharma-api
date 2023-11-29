package com.devinhouse.pharma.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FarmaciaRequest {

    @NotNull(message = "CNPJ é obrigatório")
    private Long cnpj;

    @NotBlank(message = "Razão Social é obrigatório")
    private String razaoSocial;

    @NotBlank(message = "Nome Fantasia é obrigatório}")
    private String nomeFantasia;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    private String telefone;

    @NotBlank(message = "Celular é obrigatório")
    private String celular;

    @NotNull(message = "Endereço é obrigatório")
    @Valid
    private EnderecoRequest endereco;

}


