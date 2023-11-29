package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.dto.EstoqueResponse;
import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.service.EstoqueService;
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
    public ResponseEntity<?> cadastrarEstoque(@RequestBody Estoque estoque) {
        return new ResponseEntity<>(estoqueService.cadastrarEstoque(estoque), HttpStatus.CREATED);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<EstoqueResponse>> listarEstoquePorCnpj(@PathVariable Long cnpj) {
        List<Estoque> estoques = estoqueService.listarEstoquePorCnpj(cnpj);
        var response = estoques
                .stream()
                .map(estoque -> mapper.map(estoque,EstoqueResponse.class))
                .toList();
        return ResponseEntity.ok(response);
    }
}
