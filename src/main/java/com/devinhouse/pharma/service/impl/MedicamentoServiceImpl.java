package com.devinhouse.pharma.service.impl;

import com.devinhouse.pharma.exception.RegistroJaExistenteException;
import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.repository.MedicamentoRepository;
import com.devinhouse.pharma.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Override
    public Medicamento cadastrarMedicamento(Medicamento medicamento) {
        if (medicamentoRepository.findById(medicamento.getNroRegistro()).isPresent()) {
            throw new RegistroJaExistenteException("MEDICAMENTO", medicamento.getNroRegistro());
        }
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public List<Medicamento> listarTodosMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento consultarMedicamentoPorRegistro(Integer nroRegistro) {
        return medicamentoRepository.findById(nroRegistro).get();
    }
}
