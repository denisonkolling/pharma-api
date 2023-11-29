package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.dto.FarmaciaResponse;
import com.devinhouse.pharma.dto.MedicamentoResponse;
import com.devinhouse.pharma.model.Farmacia;
import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.service.MedicamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentoService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> cadastrarMedicamento(@RequestBody Medicamento medicamento) {
        return new ResponseEntity<>(medicamentoService.cadastrarMedicamento(medicamento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> listarTodosMedicamentos() {
        List<Medicamento> medicamentos = medicamentoService.listarTodosMedicamentos();
        var response = medicamentos
                .stream()
                .map(medicamento -> mapper.map(medicamento, MedicamentoResponse.class))
                .toList();
        return ResponseEntity.ok(response);
    }

}
