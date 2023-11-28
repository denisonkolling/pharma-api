package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.dto.ErrorResponse;
import com.devinhouse.pharma.dto.FarmaciaResponse;
import com.devinhouse.pharma.model.Farmacia;
import com.devinhouse.pharma.service.FarmaciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> cadastrarFarmacia(@RequestBody Farmacia farmacia) {
        return new ResponseEntity<>(farmaciaService.cadastrarFarmacia(farmacia), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> listarTodasFarmacias() {
        List<Farmacia> farmacias = farmaciaService.listarTodasFarmacias();
        var response = farmacias
                .stream()
                .map(farmacia -> mapper.map(farmacia, FarmaciaResponse.class))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaResponse> consultarFarmaciaPorCnpj(@PathVariable("cnpj") Long cnpj) {
        Farmacia farmacia = farmaciaService.consultarFarmaciaPorCnpj(cnpj);
        FarmaciaResponse response = mapper.map(farmacia, FarmaciaResponse.class);
        return ResponseEntity.ok(response);
    }

}
