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
import jakarta.transaction.Transactional;
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
    public Estoque cadastrarEstoque(Estoque estoque) {

        if (farmaciaRepository.findById(estoque.getCnpj()).isEmpty()) {
            throw new RegistroNaoEncontradoException("CNPJ", estoque.getCnpj());
        }

        if (medicamentoRepository.findById(estoque.getNroRegistro()).isEmpty()) {
            throw new RegistroNaoEncontradoException("MEDICAMENTO", Long.valueOf(estoque.getNroRegistro()));
        }

        if (estoque.getQuantidade() <= 0) {
            throw new QuantidadeInvalidaException(estoque.getNroRegistro().toString(), estoque.getQuantidade());
        }

        var estoqueDB = estoqueRepository.findByCnpjAndNroRegistro(estoque.getCnpj(), estoque.getNroRegistro());

        if (estoqueDB == null) {
            estoqueDB = new Estoque();
            estoqueDB.setQuantidade(0);
        }

        Integer quantidadeTotal = estoque.getQuantidade() + estoqueDB.getQuantidade();
        estoque.setQuantidade(quantidadeTotal);
        estoque.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);

        return estoque;
    }

    @Override
    public List<EstoqueConsultaResponse> listarEstoquePorCnpj(Long cnpj) {

        List<Estoque> estoques = estoqueRepository.findAllByCnpj(cnpj);

        if (farmaciaRepository.findById(cnpj).isEmpty()) {
            throw new RegistroNaoEncontradoException("CNPJ", cnpj);
        }

        var listaEstoque = estoques
                .stream()
                .map(estoque -> mapper.map(estoque,EstoqueConsultaResponse.class))
                .toList();

        for (EstoqueConsultaResponse itemEstoque : listaEstoque) {
            Medicamento medicamento = medicamentoRepository.findById(itemEstoque.getNroRegistro()).get();
            itemEstoque.setNome(medicamento.getNome());
        }

        return listaEstoque;
    }

    @Override
    public Estoque deletarEstoque(Estoque request) {

        if (farmaciaRepository.findById(request.getCnpj()).isEmpty()) {
            throw new RegistroNaoEncontradoException("CNPJ", request.getCnpj());
        }

        if (medicamentoRepository.findById(request.getNroRegistro()).isEmpty()) {
            throw new RegistroNaoEncontradoException("MEDICAMENTO", Long.valueOf(request.getNroRegistro()));
        }

        if (request.getQuantidade() <= 0) {
            throw new QuantidadeInvalidaException(request.getNroRegistro().toString(), request.getQuantidade());
        }

        Estoque estoque = new Estoque();
        mapper.map(request, estoque);

        var estoqueDB = estoqueRepository.findByCnpjAndNroRegistro(request.getCnpj(), request.getNroRegistro());

        if (estoqueDB == null) {
            throw new RegistroNaoEncontradoException("ESTOQUE", estoque.getNroRegistro().toString());
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
    @Transactional
    public EstoqueTransfResponse transferenciaEstoque(EstoqueTransfRequest request) {

        if (!farmaciaRepository.existsById(request.getCnpjOrigem())) {
            throw new RegistroNaoEncontradoException("CNPJ ORIGEM", request.getCnpjOrigem());
        }

        if (!farmaciaRepository.existsById(request.getCnpjDestino())) {
            throw new RegistroNaoEncontradoException("CNPJ DESTINO", request.getCnpjDestino());
        }

        if (!medicamentoRepository.existsById(request.getNroRegistro())) {
            throw new RegistroNaoEncontradoException("MEDICAMENTO", request.getNroRegistro().toString());
        }

        if (request.getQuantidade() <= 0) {
            throw new QuantidadeInvalidaException(request.getNroRegistro().toString(), request.getQuantidade());
        }

        if(estoqueRepository.findByCnpjAndNroRegistro(request.getCnpjOrigem(), request.getNroRegistro()) == null) {
            throw new RegistroNaoEncontradoException("ESTOQUE", request.getNroRegistro().toString());
        };

        var estoqueOrigemDB = estoqueRepository.findByCnpjAndNroRegistro(request.getCnpjOrigem(), request.getNroRegistro());

        var saldoRestanteOrigem = estoqueOrigemDB.getQuantidade() - request.getQuantidade();

        if (saldoRestanteOrigem < 0) {
            throw new QuantidadeInvalidaException(request.getNroRegistro().toString(), request.getQuantidade());

        } else if (saldoRestanteOrigem == 0) {
            estoqueRepository.delete(estoqueOrigemDB);

        } else {
            estoqueOrigemDB.setQuantidade(saldoRestanteOrigem);
            estoqueRepository.save(estoqueOrigemDB);
        }

        var estoqueDestinoDB = estoqueRepository.findByCnpjAndNroRegistro(request.getCnpjDestino(), request.getNroRegistro());

        if (estoqueDestinoDB == null) {
            estoqueDestinoDB = new Estoque();
            estoqueDestinoDB.setCnpj(request.getCnpjDestino());
            estoqueDestinoDB.setNroRegistro(request.getNroRegistro());
            estoqueDestinoDB.setQuantidade(0);
            estoqueDestinoDB.setDataAtualizacao(LocalDateTime.now());
        }

        var saldoAtualizadoDestino = estoqueDestinoDB.getQuantidade() + request.getQuantidade();

        estoqueDestinoDB.setQuantidade(saldoAtualizadoDestino);
        estoqueRepository.save(estoqueDestinoDB);

        EstoqueTransfResponse estoqueResponse = new EstoqueTransfResponse();
        mapper.map(request, estoqueResponse);

        estoqueResponse.setQuantidadeOrigem(saldoRestanteOrigem);
        estoqueResponse.setQuantidadeDestino(saldoAtualizadoDestino);

        return estoqueResponse;

    }

}
