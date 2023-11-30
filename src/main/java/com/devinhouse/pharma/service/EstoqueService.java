package com.devinhouse.pharma.service;

import com.devinhouse.pharma.dto.EstoqueRequest;
import com.devinhouse.pharma.dto.EstoqueResponse;
import com.devinhouse.pharma.dto.EstoqueUpdateRequest;
import com.devinhouse.pharma.model.Estoque;

import java.util.List;


public interface EstoqueService {

    Estoque cadastrarEstoque(EstoqueRequest estoqueRequest);

    List<EstoqueResponse> listarEstoquePorCnpj(Long cnpj);

    Estoque deletarEstoque(EstoqueUpdateRequest request);

}
