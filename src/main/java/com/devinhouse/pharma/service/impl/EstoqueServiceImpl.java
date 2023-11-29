package com.devinhouse.pharma.service.impl;

import com.devinhouse.pharma.dto.EstoqueResponse;
import com.devinhouse.pharma.exception.RegistroNaoEncontradoException;
import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.repository.EstoqueRepository;
import com.devinhouse.pharma.repository.MedicamentoRepository;
import com.devinhouse.pharma.service.EstoqueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Estoque cadastrarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    @Override
    public List<EstoqueResponse> listarEstoquePorCnpj(Long cnpj) {

        List<Estoque> estoques = estoqueRepository.findAllByCnpj(cnpj);

        if (estoques.isEmpty()) {
            throw new RegistroNaoEncontradoException("CNPJ", cnpj);
        }

        var listaEstoqueResponse = estoques
                .stream()
                .map(estoque -> mapper.map(estoque,EstoqueResponse.class))
                .toList();

        for (EstoqueResponse itemEstoque : listaEstoqueResponse) {
            Medicamento medicamento = medicamentoRepository.findById(itemEstoque.getNroRegistro()).get();
            itemEstoque.setNome(medicamento.getNome());
        }

        return listaEstoqueResponse;
    }

}
