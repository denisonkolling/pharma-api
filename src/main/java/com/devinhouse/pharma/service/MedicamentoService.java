package com.devinhouse.pharma.service;

import com.devinhouse.pharma.model.Medicamento;

import java.util.List;

public interface MedicamentoService {

    Medicamento cadastrarMedicamento(Medicamento medicamento);

    List<Medicamento> listarTodosMedicamentos();

    Medicamento consultarMedicamentoPorRegistro(Integer nroRegistro);

}
