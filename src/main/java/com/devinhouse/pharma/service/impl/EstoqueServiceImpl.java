package com.devinhouse.pharma.service.impl;

import com.devinhouse.pharma.dto.*;
import com.devinhouse.pharma.exception.QuantidadeInvalidaException;
import com.devinhouse.pharma.exception.RegistroNaoEncontradoException;
import com.devinhouse.pharma.model.Estoque;
import com.devinhouse.pharma.model.Medicamento;
import com.devinhouse.pharma.repository.EstoqueRepository;
import com.devinhouse.pharma.repository.FarmaciaRepository;
import com.devinhouse.pharma.repository.MedicamentoRepository;
import com.devinhouse.pharma.service.EstoqueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Estoque cadastrarEstoque(EstoqueRequest request) {

        if (farmaciaRepository.findById(request.getCnpj()).isEmpty()) {
            throw new RegistroNaoEncontradoException("Farmácia", request.getCnpj());
        }

        if (medicamentoRepository.findById(request.getNroRegistro()).isEmpty()) {
            throw new RegistroNaoEncontradoException("Medicamento", Long.valueOf(request.getNroRegistro()));
        }

        if (request.getQuantidade() <= 0) {
            throw new QuantidadeInvalidaException(request.getNome(), request.getQuantidade());
        }

        Estoque estoque = new Estoque();
        mapper.map(request, estoque);
        var estoqueDB = estoqueRepository.findByCnpjAndNroRegistro(request.getCnpj(), request.getNroRegistro());

        if (estoqueDB == null) {
            estoqueDB = new Estoque();
            estoqueDB.setQuantidade(0);
        }

        Integer quantidadeTotal = request.getQuantidade() + estoqueDB.getQuantidade();
        estoque.setQuantidade(quantidadeTotal);
        estoque.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);

        return estoque;
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

    @Override
    public Estoque deletarEstoque(EstoqueUpdateRequest request) {

        if (farmaciaRepository.findById(request.getCnpj()).isEmpty()) {
            throw new RegistroNaoEncontradoException("Farmácia", request.getCnpj());
        }

        if (medicamentoRepository.findById(request.getNroRegistro()).isEmpty()) {
            throw new RegistroNaoEncontradoException("Medicamento", Long.valueOf(request.getNroRegistro()));
        }

        if (request.getQuantidade() <= 0) {
            throw new QuantidadeInvalidaException(request.getNroRegistro().toString(), request.getQuantidade());
        }

        Estoque estoque = new Estoque();
        mapper.map(request, estoque);

        var estoqueDB = estoqueRepository.findByCnpjAndNroRegistro(request.getCnpj(), request.getNroRegistro());

        if (estoqueDB == null) {
            throw new RegistroNaoEncontradoException("Estoque", estoque.getNroRegistro().toString());
        }

        Integer quantidadeTotal = estoqueDB.getQuantidade() - request.getQuantidade();

        if (quantidadeTotal < 0) {
            throw new QuantidadeInvalidaException(request.getNroRegistro().toString() + " estoque restante", quantidadeTotal);
        }

        estoque.setQuantidade(quantidadeTotal);
        estoque.setDataAtualizacao(LocalDateTime.now());

        if (quantidadeTotal == 0) {
            estoqueRepository.delete(estoque);
            return estoque;
        }

        estoqueRepository.save(estoque);
        return estoque;
    }

    @Override
    public EstoqueTransfResponse transferenciaEstoque(EstoqueTransfRequest request) {

        if (!farmaciaRepository.existsById(request.getCnpjOrigem())) {
            throw new RegistroNaoEncontradoException("Farmácia Origem", request.getCnpjOrigem());
        }

        if (!farmaciaRepository.existsById(request.getCnpjDestino())) {
            throw new RegistroNaoEncontradoException("Farmácia Destino", request.getCnpjDestino());
        }

        if (!medicamentoRepository.existsById(request.getNroRegistro())) {
            throw new RegistroNaoEncontradoException("Medicamento", request.getNroRegistro().toString());
        }

        return null;
    }

}
