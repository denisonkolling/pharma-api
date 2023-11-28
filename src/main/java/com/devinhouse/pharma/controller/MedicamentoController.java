package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<?> cadastrarMedicamento(@RequestBody Medicamento medicamento) {
        return new ResponseEntity<>(medicamentoService.cadastrarMedicamento(medicamento), HttpStatus.CREATED);
    }

}
