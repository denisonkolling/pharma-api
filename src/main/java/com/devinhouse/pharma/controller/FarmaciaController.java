package com.devinhouse.pharma.controller;

import com.devinhouse.pharma.model.Farmacia;
import com.devinhouse.pharma.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farmacia")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @PostMapping
    public ResponseEntity<?> cadastrarFarmacia(@RequestBody Farmacia farmacia) {
        return new ResponseEntity<>(farmaciaService.cadastrarFarmacia(farmacia), HttpStatus.CREATED);
    }

}
