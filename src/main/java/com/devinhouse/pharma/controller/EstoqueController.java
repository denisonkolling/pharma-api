package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.dto.EstoqueRequest;
import com.devinhouse.pharma.dto.EstoqueResponse;
import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;


    @PostMapping
    public ResponseEntity<?> cadastrarEstoque(@RequestBody EstoqueRequest estoqueRequest) {
        return new ResponseEntity<>(estoqueService.cadastrarEstoque(estoqueRequest), HttpStatus.OK);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<EstoqueResponse>> listarEstoquePorCnpj(@PathVariable Long cnpj) {
        return new ResponseEntity<>(estoqueService.listarEstoquePorCnpj(cnpj), HttpStatus.OK);
    }

}
