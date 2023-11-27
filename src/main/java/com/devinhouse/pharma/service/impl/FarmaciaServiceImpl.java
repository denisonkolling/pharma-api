package com.devinhouse.pharma.service.impl;

import com.devinhouse.pharma.model.Farmacia;
import com.devinhouse.pharma.repository.FarmaciaRepository;
import com.devinhouse.pharma.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaServiceImpl implements FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Override
    public Farmacia cadastrarFarmacia(Farmacia farmacia) {
        return farmaciaRepository.save(farmacia);
    }

    @Override
    public List<Farmacia> listarTodasFarmacias() {
        return farmaciaRepository.findAll();
    }

    @Override
    public Farmacia consultarFarmaciaPorCnpj(Long cnpj) {
        return farmaciaRepository.findById(cnpj).get();
    }
}
