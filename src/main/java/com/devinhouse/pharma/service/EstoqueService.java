package com.devinhouse.pharma.service;

import com.devinhouse.pharma.dto.EstoqueRequest;
import com.devinhouse.pharma.dto.EstoqueResponse;
import com.devinhouse.pharma.model.Estoque;

import java.util.List;


public interface EstoqueService {

    // TODO RF08 - Aquisição de Medicamentos para Estoque de farmácia

    Estoque cadastrarEstoque(EstoqueRequest estoqueRequest);

    // TODO RF07 - Consulta de Estoque de Farmácia

    List<EstoqueResponse> listarEstoquePorCnpj(Long cnpj);

    // TODO RF09 - Venda de Medicamentos com atualização do Estoque de farmácia

    // TODO RF10 -Troca de Medicamentos entre Estoques de Farmácias

}
