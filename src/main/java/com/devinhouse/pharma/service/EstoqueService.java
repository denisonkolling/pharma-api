package com.devinhouse.pharma.service;

import com.devinhouse.pharma.dto.*;
import com.devinhouse.pharma.model.Estoque;

import java.util.List;


public interface EstoqueService {

    Estoque cadastrarEstoque(Estoque estoque);

    List<EstoqueConsultaResponse> listarEstoquePorCnpj(Long cnpj);

    Estoque deletarEstoque(Estoque request);

    EstoqueTransfResponse transferenciaEstoque(EstoqueTransfRequest request);

}
