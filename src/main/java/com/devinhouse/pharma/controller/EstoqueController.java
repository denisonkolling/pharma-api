package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.dto.EstoqueResponse;
import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.repository.MedicamentoRepository;
import com.devinhouse.pharma.service.EstoqueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

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

        for (EstoqueResponse estoqueResponse : response) {
            Medicamento medicamento = medicamentoRepository.findById(estoqueResponse.getNroRegistro()).get();
            estoqueResponse.setNome(medicamento.getNome());
        }
        return ResponseEntity.ok(response);
    }
}
