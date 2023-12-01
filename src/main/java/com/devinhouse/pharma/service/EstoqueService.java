package com.devinhouse.pharma.service;

import com.devinhouse.pharma.dto.*;
import com.devinhouse.pharma.model.Estoque;

import java.util.List;


public interface EstoqueService {

    Estoque cadastrarEstoque(EstoqueRequest estoqueRequest);

    List<EstoqueResponse> listarEstoquePorCnpj(Long cnpj);

    Estoque deletarEstoque(EstoqueUpdateRequest request);

    EstoqueTransfResponse transferenciaEstoque(EstoqueTransfRequest request);

}
