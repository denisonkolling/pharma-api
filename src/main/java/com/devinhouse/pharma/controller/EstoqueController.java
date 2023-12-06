package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.dto.*;
import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.service.EstoqueService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<EstoqueResponse> cadastrarEstoque(@RequestBody EstoqueRequest request) {
        var estoque = mapper.map(request, Estoque.class);
        estoque = estoqueService.cadastrarEstoque(estoque);
        var response = mapper.map(estoque, EstoqueResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<EstoqueConsultaResponse>> listarEstoquePorCnpj(@PathVariable Long cnpj) {
        return new ResponseEntity<>(estoqueService.listarEstoquePorCnpj(cnpj), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<EstoqueResponse> deletarEstoque(@RequestBody @Valid EstoqueRequest request) {
        var estoque = mapper.map(request, Estoque.class);
        estoque = estoqueService.deletarEstoque(estoque);
        var response = mapper.map(estoque, EstoqueResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EstoqueTransfResponse> tranferenciaEstoque(@RequestBody @Valid EstoqueTransfRequest request) {
        return new ResponseEntity<>(estoqueService.transferenciaEstoque(request), HttpStatus.OK);
    }

}
