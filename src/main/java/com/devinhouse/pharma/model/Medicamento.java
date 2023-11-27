package com.devinhouse.pharma.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "MEDICAMENTOS")
public class Medicamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nroRegistro;

    private String nome;

    private String laboratorio;

    private String dosagem;

    private String descricao;

    private Float preco;

    @Enumerated(EnumType.STRING)
    private TipoMedicamento tipo;

}
