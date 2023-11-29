package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.dto.FarmaciaRequest;
import com.devinhouse.pharma.dto.FarmaciaResponse;
import com.devinhouse.pharma.dto.MedicamentoRequest;
import com.devinhouse.pharma.dto.MedicamentoResponse;
import com.devinhouse.pharma.model.Farmacia;
import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.service.MedicamentoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<MedicamentoResponse> cadastrarMedicamento(@RequestBody @Valid MedicamentoRequest medicamentoRequest) {
        var medicamento = mapper.map(medicamentoRequest, Medicamento.class);
        medicamento = medicamentoService.cadastrarMedicamento(medicamento);
        var response = mapper.map(medicamento,MedicamentoResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
