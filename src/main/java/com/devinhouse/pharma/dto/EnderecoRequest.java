package com.devinhouse.pharma.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoRequest {

    @NotNull(message = "CEP é obrigatório")
    private Long cep;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotNull(message = "Número é obrigatório")
    private Integer numero;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    private String complemento;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

}
