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
    public ResponseEntity<Estoque> cadastrarEstoque(@RequestBody EstoqueRequest estoqueRequest) {
        var estoque = mapper.map(estoqueRequest, Estoque.class);
        return new ResponseEntity<>(estoqueService.cadastrarEstoque(estoque), HttpStatus.OK);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<EstoqueResponse>> listarEstoquePorCnpj(@PathVariable Long cnpj) {
        return new ResponseEntity<>(estoqueService.listarEstoquePorCnpj(cnpj), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Estoque> deletarEstoque(@RequestBody @Valid EstoqueUpdateRequest request) {
        return new ResponseEntity<>(estoqueService.deletarEstoque(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EstoqueTransfResponse> tranferenciaEstoque(@RequestBody @Valid EstoqueTransfRequest request) {
        return new ResponseEntity<>(estoqueService.transferenciaEstoque(request), HttpStatus.OK);
    }

}
