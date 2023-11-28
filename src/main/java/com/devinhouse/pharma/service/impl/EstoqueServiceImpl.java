package com.devinhouse.pharma.service.impl;

import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.repository.EstoqueRepository;
import com.devinhouse.pharma.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Override
    public Estoque cadastrarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    @Override
    public List<Estoque> consultarEstoquePorCnpj(Long cnpj) {
        return estoqueRepository.findAllByCnpj(cnpj);
    }
}
